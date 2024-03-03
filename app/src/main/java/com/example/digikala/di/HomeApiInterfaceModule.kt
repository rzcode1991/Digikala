package com.example.digikala.di

import com.example.digikala.data.network.HomeApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeApiInterfaceModule {

    @Provides
    @Singleton
    fun provideHomeApiInterface(retrofit: Retrofit): HomeApiInterface =
        retrofit.create(HomeApiInterface::class.java)

}