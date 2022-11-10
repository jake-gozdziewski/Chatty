package com.muzz.chatty.model

/**
 * Model for the chat message to be consumed by the UI.
 *
 * [header] is intended to be a single string, only to be shown if not empty.
 */
data class ChatMessage(
    val content: String,
    val header: String = "",
    val isReply: Boolean,
    val showTail: Boolean
)