package com.muzz.chatty.data.room

import kotlinx.coroutines.flow.Flow

/** Component used for interacting with the persisted messages between users */
interface MessageRepository {

    /** Get all messages as Kotlin [Flow] */
    fun getMessages(): Flow<List<Message>>

    /** Add a message with given [content], [userId], and [timeSent] to the database */
    suspend fun addMessage(
        content: String,
        userId: Int,
        timeSent: Long = System.currentTimeMillis()
    )

    /** Restore the database to its original state */
    suspend fun restoreOriginalContent()
}