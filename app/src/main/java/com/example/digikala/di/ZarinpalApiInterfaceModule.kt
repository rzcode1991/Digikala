package com.example.digikala.di

import com.example.digikala.data.network.ZarinpalApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ZarinpalApiInterfaceModule {

    @Provides
    @Singleton
    fun provideZarinpalApiInterface(@Named("zarinRetrofit") retrofit: Retrofit): ZarinpalApiInterface =
        retrofit.create(ZarinpalApiInterface::class.java)

}