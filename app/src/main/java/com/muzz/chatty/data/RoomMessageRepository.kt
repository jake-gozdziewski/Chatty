package com.muzz.chatty.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

/** Room-based implementation of [MessageRepository] */
class RoomMessageRepository @Inject constructor(
    private val database: MessageDatabase
): MessageRepository {
    private val messageDao = database.messageDao()

    override fun getMessages(): Flow<List<Message>> = messageDao.getMessages()

    override suspend fun addMessage(content: String, userId: Int, timeSent: Long) {
        withContext(Dispatchers.IO) {
            messageDao.insert(
                Message(
                    content = content,
                    userId = userId,
                    timeSent = timeSent
                )
            )
        }
    }

    override suspend fun restoreOriginalContent() {
        withContext(Dispatchers.IO) {
            database.clearAllTables()
        }
    }
}