package com.muzz.chatty.data.realm

import com.muzz.chatty.data.getSampleMessages
import com.muzz.chatty.data.room.Message
import com.muzz.chatty.data.room.MessageRepository
import io.realm.kotlin.Realm
import io.realm.kotlin.ext.query
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

/** Realm-based implementation of [MessageRepository] */
class RealmMessageRepository @Inject constructor(
    private val realm: Realm
): MessageRepository {
    override fun getMessages(): Flow<List<Message>> = realm.query<RealmMessage>().find().asFlow()
        .map { results -> results.list.toList().map { message -> message.toDataClass() } }

    override suspend fun addMessage(content: String, userId: Int, timeSent: Long) {
        withContext(Dispatchers.IO) {
            realm.write {
                copyToRealm(RealmMessage().apply {
                    this.content = content
                    this.userId = userId
                    this.timeSent = timeSent
                })
            }
        }
    }

    override suspend fun restoreOriginalContent() {
        withContext(Dispatchers.IO) {
            realm.write {
                deleteAll()

                getSampleMessages().map { roomMessage ->
                    RealmMessage().apply {
                        userId = roomMessage.userId
                        content = roomMessage.content
                        timeSent = roomMessage.timeSent
                    }
                }.forEach { copyToRealm(it) }
            }
        }
    }
}