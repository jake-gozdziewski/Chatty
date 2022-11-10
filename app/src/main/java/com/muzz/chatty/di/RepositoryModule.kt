package com.muzz.chatty.di

import com.muzz.chatty.data.MessageRepository
import com.muzz.chatty.data.RoomMessageRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/** Binds [RoomMessageRepository] to [MessageRepository] for Hilt dependency injection */
@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Singleton
    @Binds
    abstract fun bindRepository(repository: RoomMessageRepository): MessageRepository
}