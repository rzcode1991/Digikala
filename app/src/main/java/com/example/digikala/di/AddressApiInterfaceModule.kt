package com.example.digikala.di

import com.example.digikala.data.network.AddressApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AddressApiInterfaceModule {

    @Provides
    @Singleton
    fun provideAddressApiInterface(retrofit: Retrofit): AddressApiInterface =
        retrofit.create(AddressApiInterface::class.java)

}