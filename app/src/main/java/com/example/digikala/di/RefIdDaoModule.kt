package com.example.digikala.di

import com.example.digikala.data.db.DigikalaDatabase
import com.example.digikala.data.db.RefIdDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RefIdDaoModule {

    @Provides
    @Singleton
    fun provideRefIdDao(database: DigikalaDatabase): RefIdDao = database.refIdDao()

}