package com.muzz.chatty.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.muzz.chatty.ui.theme.BasicPreview
import com.muzz.chatty.ui.theme.ChattyTheme


/** Component for entering and sending text messages */
@Composable
fun TextEntryBox(
    sendMessage: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = Modifier
            .fillMaxWidth()
            .then(modifier)
            .heightIn(max = 40.dp)
    ) {
        var text by remember { mutableStateOf("") }

        ChattyTextField(
            text = text,
            onTextChanged = { text = it },
            modifier = Modifier
                .weight(1f)
                .fillMaxHeight()
        )

        SendButton(
            onClick = {
                if (text.isNotBlank()) sendMessage(text)
                text = ""
            },
            modifier = Modifier.fillMaxHeight(),
            iconModifier = Modifier
                .padding(6.dp)
                .offset(x = 1.dp)
        )
    }
}


@BasicPreview
@Composable
internal fun TextEntryBoxPreview() {
    ChattyTheme {
        TextEntryBox(
            sendMessage = { },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .padding(top = 8.dp, bottom = 16.dp)
                .requiredHeight(40.dp)
        )
    }
}