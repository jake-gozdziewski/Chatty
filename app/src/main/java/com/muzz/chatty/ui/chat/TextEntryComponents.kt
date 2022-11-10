package com.muzz.chatty.ui.chat

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.muzz.chatty.R
import com.muzz.chatty.ui.OutlinedTextField
import com.muzz.chatty.ui.theme.BasicPreview
import com.muzz.chatty.ui.theme.ChattyTheme
import com.muzz.chatty.ui.theme.MuzzPink
import com.muzz.chatty.ui.theme.MuzzYellow

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
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .fillMaxWidth()
            )
        }
    }
}



/** Button to send a text message */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SendButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    iconModifier: Modifier = Modifier
) {
    Surface(
        shape = CircleShape,
        modifier = modifier.aspectRatio(1f),
        onClick = onClick,
    ) {
        Icon(
            imageVector = Icons.Rounded.Send,
            contentDescription = null,
            modifier = Modifier
                .background(Brush.verticalGradient(listOf(MuzzPink, MuzzYellow)))
                .then(iconModifier),
            tint = Color.White
        )
    }
}


@BasicPreview
@Composable
fun SendButtonPreview() {
    ChattyTheme {
        SendButton(
            onClick = {  },
            modifier = Modifier.requiredSize(40.dp),
            iconModifier = Modifier.offset(x = 1.dp).padding(6.dp)
        )
    }
}