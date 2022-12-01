package com.muzz.chatty.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/** Entity model for the Room database, with [userId] to mock-up the message sender. */
@Entity(tableName = "messages")
data class Message(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "message_id", defaultValue = "1")
    val messageId: Int = 0,
    // In production, possibly better alternative is conversationId,
    // Which could be a foreign key that maps to users
    @ColumnInfo(name = "user_id")
    val userId: Int = 0,
    @ColumnInfo(name = "content")
    val content: String = "",
    // In milliseconds since last epoch, simpler and smaller than timestamps
    @ColumnInfo(name = "time_sent")
    val timeSent: Long = 0L
)