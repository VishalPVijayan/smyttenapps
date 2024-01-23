package com.vishalpvijayan.smyttens.application

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.vishalpvijayan.smyttens.database.AppDatabase
import com.vishalpvijayan.smyttens.viewModels.LauncherViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideContext(@ApplicationContext appContext: Context): Context {
        return appContext
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, "my-database"
        ).build()
    }


}