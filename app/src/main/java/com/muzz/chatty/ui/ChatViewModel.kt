package com.muzz.chatty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzz.chatty.data.MessageRepository
import com.muzz.chatty.model.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: MessageRepository
): ViewModel() {

    val messageList: Flow<List<ChatMessage>> = repository.getMessages().map { messageList ->
        messageList.map { message ->
            ChatMessage(
                content = message.content,
                isReply = message.userId != 0,
                header = "", // TODO: Implement logic
                showTail = true // TODO: Implement logic
            )
        }
    }

    fun sendMessage(text: String, isReply: Boolean) = viewModelScope.launch {
        repository.addMessage(
            content = text,
            userId = if (isReply) 1 else 0
        )
    }

    fun restoreContent() = viewModelScope.launch {
        repository.restoreOriginalContent()
    }

    fun changeUser() { }

}