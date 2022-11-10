package com.muzz.chatty.ui.chat

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muzz.chatty.model.ChatMessage
import com.muzz.chatty.ui.theme.BasicPreview
import com.muzz.chatty.ui.theme.ChattyTheme

/**
 * Vertical list of [ChatMessage]s provided.
 * Uses double-reverse trick to ensure the list properly adjusts its scroll position
 * when the window bounds are changed (i.e. when the soft input keyboard is shown).
 */
@Composable
fun Conversation(
    messageList: List<ChatMessage>,
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp),
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(12.dp),
        reverseLayout = true
    ) {
        items(messageList.reversed()) { message ->
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                if (message.header.isNotBlank()) {
                    ChatHeader(
                        text = message.header,
                        modifier = Modifier.padding(bottom = 12.dp)
                    )
                }
                
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
internal fun ConversationPreview() {
    ChattyTheme {
        Conversation(
            messageList = listOf(
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
            ),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp)
        )
    }
}