package com.wisnu.moviedb.data.network.retrofit

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.wisnu.moviedb.BuildConfig
import com.wisnu.moviedb.data.network.model.ApiClient
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.reflect.Type
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by wisnu on 13/06/2017.
 */
@Module
class RetrofitModule {

    @Singleton
    @Provides
    fun provideServerApi(@Named("server_retrofit") retrofit: Retrofit): ApiClient {
        return retrofit.create(ApiClient::class.java)
    }

    @Singleton
    @Provides
    @Named("server_retrofit")
    fun provideServerRetrofit(okHttpClient: OkHttpClient,
                              @Named("base_url") baseUrl: String,
                              callAdapterFactory: CallAdapter.Factory,
                              @Named("gson_converter") gsonConverter: Converter.Factory,
                              @Named("any_on_empty_converter") anyOnEmptyConverter: Converter.Factory): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl)
            .addCallAdapterFactory(callAdapterFactory)
            .addConverterFactory(anyOnEmptyConverter)
            .addConverterFactory(gsonConverter)
            .build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(@Named("http_logging_interceptor") loggingInterceptor: Interceptor,
                            @Named("authorization_interceptor") authorizationInterceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(authorizationInterceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    @Singleton
    @Provides
    @Named("http_logging_interceptor")
    fun provideHttpLoggingInterceptor(): Interceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    @Singleton
    @Provides
    @Named("authorization_interceptor")
    fun provideAuthorizationInterceptor(@Named("api_key") apiKey: String): Interceptor {
        return Interceptor { chain ->
            val request = chain.request()
            val bodyInterceptedRequest = request.newBuilder()
                .url(request.url().newBuilder().setQueryParameter("api_key", apiKey).build())
                .build()

            chain.proceed(bodyInterceptedRequest)
        }
    }

    @Provides
    @Named("base_url")
    fun provideBaseUrl(): String {
        return BuildConfig.BASE_URL
    }

    @Provides
    @Named("api_key")
    fun provideApiKey(): String {
        return BuildConfig.API_KEY
    }

    @Singleton
    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory {
        return RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    @Singleton
    @Provides
    @Named("gson_converter")
    fun provideConverterFactory(@Named("lower_case_with_underscores_gson") gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Singleton
    @Provides
    @Named("lower_case_with_underscores_gson")
    fun provideGson(): Gson {
        return GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create()
    }

    @Singleton
    @Provides
    @Named("any_on_empty_converter")
    fun provideAnyOnEmptyConverter(): Converter.Factory {
        return object : Converter.Factory() {
            override fun responseBodyConverter(type: Type, annotations: Array<Annotation>, retrofit: Retrofit): Converter<ResponseBody, Any> {
                val delegate: Converter<ResponseBody, Any> = retrofit.nextResponseBodyConverter(this, type, annotations)
                return Converter { body ->
                    if (body.contentLength() == 0L) return@Converter null
                    delegate.convert(body)
                }
            }
        }
    }

}