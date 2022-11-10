package com.muzz.chatty.ui.chat

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person4
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.muzz.chatty.ui.theme.BasicPreview
import com.muzz.chatty.ui.theme.ChattyTheme

@Composable
fun ChattyAppBar(
    userName: String,
    userAvatar: ImageVector,
    onBackClick: () -> Unit,
    onEllipsisClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        modifier = modifier,
        backgroundColor = Color.White,
        elevation = 4.dp
    ) {
        BackButton(
            onClick = onBackClick,
            modifier = Modifier.size(56.dp)
        )

        UserHeader(
            name = userName,
            avatar = userAvatar,
            modifier = Modifier.weight(1f).padding(horizontal = 12.dp)
        )

        EllipsisButton(
            onClick = onEllipsisClick,
            modifier = Modifier.size(48.dp).offset(y = 2.dp)
        )
    }
}


@BasicPreview
@Composable
internal fun ChattyAppBarPreview() {
    ChattyTheme {
        ChattyAppBar(
            userName = "Bob",
            userAvatar = Icons.Default.Person4,
            onBackClick = { },
            onEllipsisClick = { },
            //modifier = Modifier.requiredHeight(56.dp)
        )
    }
}