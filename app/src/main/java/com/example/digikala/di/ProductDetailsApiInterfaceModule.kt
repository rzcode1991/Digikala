package com.example.digikala.di

import com.example.digikala.data.network.ProductDetailsApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductDetailsApiInterfaceModule {

    @Provides
    @Singleton
    fun provideProductDetailsApiInterface(@Named("digiRetrofit") retrofit: Retrofit): ProductDetailsApiInterface =
        retrofit.create(ProductDetailsApiInterface::class.java)

}