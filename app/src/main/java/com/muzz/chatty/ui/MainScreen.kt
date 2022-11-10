package com.muzz.chatty.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person4
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muzz.chatty.model.ChatMessage
import com.muzz.chatty.ui.chat.ChattyAppBar
import com.muzz.chatty.ui.chat.Conversation
import com.muzz.chatty.ui.chat.TextEntryBox

@Composable
fun MainScreen(
    // TODO: Add ViewModel
) {
    Scaffold(
        topBar = {
            ChattyAppBar(
                userName = "Bob",
                userAvatar = Icons.Default.Person4,
                onBackClick = { },
                onEllipsisClick = { },
            )
        },
        bottomBar = {
            TextEntryBox(
                sendMessage = { },
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .padding(top = 8.dp, bottom = 16.dp)
                    .requiredHeight(40.dp)
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .padding(paddingValues),
            contentAlignment = Alignment.BottomCenter
        ) {
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
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
                )
        }
    }
}