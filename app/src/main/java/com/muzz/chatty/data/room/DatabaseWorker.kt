package com.muzz.chatty.data.room

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.muzz.chatty.data.getSampleMessages
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class DatabaseWorker @AssistedInject constructor(
    @Assisted appContext: Context,
    @Assisted workerParams: WorkerParameters,
    private val database: MessageDatabase
): CoroutineWorker(appContext, workerParams) {

    override suspend fun doWork(): Result = try {
        database.messageDao().insert(*getSampleMessages().toTypedArray())
        Result.success()
    } catch (e: Exception) {
        e.printStackTrace()
        Result.failure()
    }
}



