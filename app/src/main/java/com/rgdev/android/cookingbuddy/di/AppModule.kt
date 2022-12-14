package com.rgdev.android.cookingbuddy.di

import android.app.Application
import com.rgdev.android.cookingbuddy.data.repository.DataRepository
import com.rgdev.android.cookingbuddy.data.repository.DataRepositoryImpl
import com.rgdev.android.cookingbuddy.data.db.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDataRepository(app: Application): DataRepository {
        return DataRepositoryImpl(AppDatabase.getInstance(app).dbDao())
    }

}