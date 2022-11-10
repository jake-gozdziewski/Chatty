package com.muzz.chatty.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muzz.chatty.model.ChatMessage
import com.muzz.chatty.ui.theme.BasicPreview
import com.muzz.chatty.ui.theme.ChattyTheme

/** Vertical list of [ChatMessage]s provided */
@Composable
fun Conversation(
    messageList: List<ChatMessage>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(messageList) { message ->
            Column {
                // TODO: Header text
                
                if (message.isReply) {
                    ReplyChip(text = message.content, showTail = message.showTail)
                } else {
                    MessageChip(text = message.content, showTail = message.showTail)
                }
            }            
        }
    }
}


@BasicPreview
@Composable
fun ConversationPreview() {
    ChattyTheme {
        Conversation(
            messageList = listOf(
                ChatMessage(
                    content = "Yeh for sure that works. What time do you think?",
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
}