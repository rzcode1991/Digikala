package com.example.digikala.di

import com.example.digikala.data.network.BasketApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object BasketApiInterfaceModule {

    @Provides
    @Singleton
    fun provideBasketApiInterface(@Named("digiRetrofit") retrofit: Retrofit): BasketApiInterface =
        retrofit.create(BasketApiInterface::class.java)

}