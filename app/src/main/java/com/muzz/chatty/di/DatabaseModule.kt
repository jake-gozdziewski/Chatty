package com.muzz.chatty.di

import android.content.Context
import com.muzz.chatty.data.room.MessageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Allows for the use of [MessageDatabase] as part of dependency injection with Hilt */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideMessageDatabase(@ApplicationContext context: Context) : MessageDatabase {
        return MessageDatabase.getInstance(context)
    }
}