package com.muzz.chatty.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person4
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muzz.chatty.ui.chat.ChattyAppBar
import com.muzz.chatty.ui.chat.Conversation
import com.muzz.chatty.ui.chat.TextEntryBox

@Composable
fun MainScreen(
    viewModel: ChatViewModel
) {
    val messageList by viewModel.messageList.collectAsState(initial = emptyList())

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            ChattyAppBar(
                userName = "Bob",
                userAvatar = Icons.Default.Person4,
                onBackClick = viewModel::restoreContent,
                onEllipsisClick = viewModel::changeUser,
            )
        },
        bottomBar = {
            TextEntryBox(
                sendMessage = { viewModel.sendMessage(text = it, isReply = false) },
                modifier = Modifier
                    .imePadding()
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
                    messageList = messageList,
                    contentPadding = PaddingValues(horizontal = 16.dp, vertical = 24.dp),
                )
        }
    }
}