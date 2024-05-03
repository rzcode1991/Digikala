package com.example.digikala.di

import com.example.digikala.utils.Constants.API_KEY
import com.example.digikala.utils.Constants.BASE_URL
import com.example.digikala.utils.Constants.TIMEOUT_SECONDS
import com.example.digikala.utils.Constants.USER_LANGUAGE
import com.example.digikala.utils.Constants.ZARIN_BASE_URL
import com.example.digikala.utils.Constants.ZARIN_BASE_URL_SANDBOX
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    internal fun interceptor(): HttpLoggingInterceptor{
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BODY)
        return logging
    }

    @Provides
    @Singleton
    @Named("digiHttpClient")
    fun provideDigiOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("x-api-key", API_KEY)
                .addHeader("lang", USER_LANGUAGE)
            chain.proceed(request.build())
        }
        .addInterceptor(interceptor())
        .build()

    @Provides
    @Singleton
    @Named("zarinHttpClient")
    fun provideZarinOkHttpClient(): OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        .addInterceptor { chain ->
            val request = chain.request().newBuilder()
                .addHeader("accept", "application/json")
                .addHeader("content-type", "application/json")
            chain.proceed(request.build())
        }
        .addInterceptor(interceptor())
        .build()

    @Provides
    @Singleton
    @Named("digiRetrofit")
    fun provideDigiRetrofit(@Named("digiHttpClient") client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    @Provides
    @Singleton
    @Named("zarinRetrofit")
    fun provideNewRetrofit(@Named("zarinHttpClient") client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(ZARIN_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()


}