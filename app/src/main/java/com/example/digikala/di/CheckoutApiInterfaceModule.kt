package com.example.digikala.di

import com.example.digikala.data.network.CheckoutApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CheckoutApiInterfaceModule {

    @Provides
    @Singleton
    fun provideCheckoutApiInterface(@Named("digiRetrofit") retrofit: Retrofit): CheckoutApiInterface =
        retrofit.create(CheckoutApiInterface::class.java)

}