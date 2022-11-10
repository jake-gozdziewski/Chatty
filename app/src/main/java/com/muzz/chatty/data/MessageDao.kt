package com.muzz.chatty.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/** Access functions for the [Message] table. */
@Dao
interface MessageDao {

    /** Insert one or more [message]s into the database, replacing any conflicting entries. */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg message: Message)

    /** Delete one or more [message]s from the database. */
    @Delete
    suspend fun delete(vararg message: Message)

    /** Get all messages from the database. */
    @Query("SELECT * FROM messages ORDER BY time_sent")
    fun getMessages(): Flow<List<Message>>
}