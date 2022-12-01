package com.muzz.chatty.data.realm

import com.muzz.chatty.data.room.Message
import io.realm.kotlin.types.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

/** Realm version of [com.muzz.chatty.data.room.Message] */
open class RealmMessage(): RealmObject {
    @PrimaryKey
    var messageId: ObjectId = ObjectId.create()
    var userId: Int = 0
    var content: String = ""
    var timeSent: Long = System.currentTimeMillis()

    var owner_id: String = ""

    constructor(ownerId: String = "") : this() {
        owner_id = ownerId
    }

    fun toDataClass(): Message = Message(
        userId = userId,
        content = content,
        timeSent = timeSent
    )
}