package com.example.digikala.di

import android.content.Context
import androidx.room.Room
import com.example.digikala.data.db.Converters
import com.example.digikala.data.db.DigikalaDatabase
import com.example.digikala.utils.Constants.DATABASE_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataBaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context,
        converters: Converters
    ) = Room.databaseBuilder(
        context,
        DigikalaDatabase::class.java,
        DATABASE_NAME
    ).addMigrations(
        DigikalaDatabase.MIGRATION_2_3,
        DigikalaDatabase.MIGRATION_3_4
    )
        .addTypeConverter(converters)
        .build()

    @Provides
    @Singleton
    fun provideConverters(): Converters {
        return Converters()
    }

}