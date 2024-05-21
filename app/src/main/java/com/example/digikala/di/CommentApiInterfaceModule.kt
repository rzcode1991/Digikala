package com.example.digikala.di

import com.example.digikala.data.network.CommentApiInterface
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommentApiInterfaceModule {

    @Provides
    @Singleton
    fun provideCommentApiInterface(@Named("digiRetrofit") retrofit: Retrofit): CommentApiInterface =
        retrofit.create(CommentApiInterface::class.java)

}