package com.example.digikala.di

import com.example.digikala.data.db.DigikalaDatabase
import com.example.digikala.data.db.FavoritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FavoritesDaoModule {

    @Provides
    @Singleton
    fun provideFavoritesDao(
        database: DigikalaDatabase
    ): FavoritesDao = database.favoritesDao()

}