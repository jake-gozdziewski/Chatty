package com.muzz.chatty.ui

import androidx.lifecycle.ViewModel
import com.muzz.chatty.model.ChatMessage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ChatViewModel: ViewModel() {

    val messageList: Flow<List<ChatMessage>> = flow {
        emit(
            listOf(
                ChatMessage(
                    content = "Yeh for sure that works. What time do you think?",
                    header = "Monday 21:57",
                    isReply = true,
                    showTail = false
                ),
                ChatMessage(
                    content = "Does 7pm work for you? I've got to go pick up my little brother first from a party",
                    isReply = false,
                    showTail = true
                ),
                ChatMessage(
                    content = "Ok cool!",
                    header = "Tuesday 12:36",
                    isReply = true,
                    showTail = true
                ),
                ChatMessage(
                    content = "What are you up to today?",
                    isReply = false,
                    showTail = false
                )
            )
        )
    }

    fun sendMessage(text: String, isReply: Boolean) { }

    fun restoreContent() { }

    fun changeUser() { }

}