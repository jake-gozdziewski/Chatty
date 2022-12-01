package com.muzz.chatty.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

/** Database for storing messages in the app */
@Database(
    version = 1,
    entities = [Message::class]
)
abstract class MessageDatabase: RoomDatabase() {

    abstract fun messageDao(): MessageDao

    companion object {
        @Volatile
        private var instance: MessageDatabase? = null

        // Get database object instance
        fun getInstance(context: Context): MessageDatabase {
            // If the INSTANCE is not null, then return it. If it is, then create the database
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        // Build the database
        private fun buildDatabase(context: Context): MessageDatabase {
            return Room.databaseBuilder(context, MessageDatabase::class.java, "message_db")
                .addCallback(object: Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<DatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }
}