package com.muzz.chatty.di

import android.content.Context
import com.muzz.chatty.data.realm.RealmMessage
import com.muzz.chatty.data.room.MessageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.internal.platform.RealmInitializer
import javax.inject.Singleton

/** Allows for the use of [MessageDatabase] and [Realm] as part of dependency injection with Hilt */
@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideMessageDatabase(@ApplicationContext context: Context) : MessageDatabase {
        return MessageDatabase.getInstance(context)
    }

    @Singleton
    @Provides
    fun provideRealmDatabase(@ApplicationContext context: Context) : Realm {
        RealmInitializer().create(context = context)
        return Realm.open(RealmConfiguration.create(schema = setOf(RealmMessage::class)))
    }
}