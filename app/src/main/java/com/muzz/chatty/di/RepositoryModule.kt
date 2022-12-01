package com.muzz.chatty.di

import com.muzz.chatty.data.realm.RealmMessageRepository
import com.muzz.chatty.data.room.MessageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Binds [RealmMessageRepository] to [MessageRepository] for Hilt dependency injection */
@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(repository: RealmMessageRepository): MessageRepository
}