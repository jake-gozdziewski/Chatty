package com.muzz.chatty.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.muzz.chatty.data.MessageRepository
import com.muzz.chatty.model.ChatMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import java.time.Instant
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class ChatViewModel @Inject constructor(
    private val repository: MessageRepository
): ViewModel() {

    private val _isUserSarah = MutableStateFlow(false)
    val isUserSarah = _isUserSarah.asStateFlow()

    fun changeUser() { _isUserSarah.value = !_isUserSarah.value }


    val messageList: Flow<List<ChatMessage>> =
        combine(repository.getMessages(), isUserSarah) { messageList, isUserSarah ->
            messageList.mapIndexed { index, message ->
                ChatMessage(
                    content = message.content,
                    isReply = message.userId != if (isUserSarah) 1 else 0,
                    header = if (
                        index == 0 || message.timeSent - messageList[index - 1].timeSent > 1.hours
                    ) makeHeader(message.timeSent) else "",
                    showTail = index == messageList.size - 1
                            || messageList[index + 1].userId != message.userId
                            || messageList[index + 1].timeSent - message.timeSent > 20.seconds
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


    private fun makeHeader(timeSent: Long): String =
        ZonedDateTime.ofInstant(Instant.ofEpochMilli(timeSent), ZoneId.systemDefault())
            .format(DateTimeFormatter.ofPattern("EEEE HH:mm"))

    private val Int.hours: Long get() = this.seconds * 3600
    private val Int.seconds: Long get() = this * 1_000L

}