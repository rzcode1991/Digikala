package com.example.digikala.di

import com.example.digikala.data.db.DigikalaDatabase
import com.example.digikala.data.db.OrderDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderDaoModule {

    @Provides
    @Singleton
    fun provideOrderDao(
        database: DigikalaDatabase
    ): OrderDao = database.orderDao()

}