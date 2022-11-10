package com.muzz.chatty.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.muzz.chatty.R
import com.muzz.chatty.ui.OutlinedTextField
import com.muzz.chatty.ui.theme.BasicPreview
import com.muzz.chatty.ui.theme.ChattyTheme

/** Text field intended to be used for message input */
@Composable
fun ChattyTextField(
    text: String,
    onTextChanged: (String) -> Unit,
    modifier: Modifier = Modifier
) {

    OutlinedTextField(
        value = text,
        onValueChange = onTextChanged,
        modifier = modifier,
        shape = RoundedCornerShape(percent = 50),
        placeholder = {
            Text(text = stringResource(id = R.string.match_placeholder))
        }
    )
}


@BasicPreview
@Composable
internal fun ChattyTextFieldPreview() {
    var text by remember { mutableStateOf("") }

    ChattyTheme {
        Box(modifier = Modifier
            .height(72.dp)
            .padding(horizontal = 32.dp)
        ) {
            ChattyTextField(
                text = text,
                onTextChanged = { text = it },
                modifier = Modifier.padding(vertical = 16.dp).fillMaxWidth()
            )
        }
    }
}