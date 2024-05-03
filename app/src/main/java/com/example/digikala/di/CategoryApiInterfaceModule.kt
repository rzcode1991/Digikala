package com.example.digikala.di

import com.example.digikala.data.network.CategoryApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CategoryApiInterfaceModule {

    @Provides
    @Singleton
    fun provideCategoryApiInterface(@Named("digiRetrofit") retrofit: Retrofit): CategoryApiInterface =
        retrofit.create(CategoryApiInterface::class.java)

}