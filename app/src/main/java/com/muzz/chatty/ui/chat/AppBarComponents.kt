package com.muzz.chatty.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.material.icons.filled.Person3
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.muzz.chatty.ui.theme.*

/** Back button for the top app bar */
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BackButton(
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = CircleShape,
        onClick = onClick
    ) {
        Icon(
            imageVector = Icons.Default.ChevronLeft,
            contentDescription = null,
            // Gradient solution based on https://stackoverflow.com/a/70474546
            modifier = modifier
                .graphicsLayer(alpha = 0.99f)
                .drawWithCache {
                    onDrawWithContent {
                        drawContent()
                        drawRect(
                            brush = Brush.verticalGradient(listOf(MuzzPink, MuzzYellow)),
                            blendMode = BlendMode.SrcAtop
                        )
                    }
                }
        )
    }
}


@BasicPreview
@Composable
internal fun BackButtonPreview() {
    ChattyTheme {
        BackButton(
            onClick = {  },
            modifier = Modifier.requiredSize(56.dp)
        )
    }
}



/** Header for the user [avatar] and [name]. Using icon instead of picture for simplicity */
@Composable
fun UserHeader(
    name: String,
    avatar: ImageVector,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Surface(
            color = MuzzGray,
            shape = CircleShape,
            modifier = Modifier.size(30.dp)
        ) {
            Icon(
                imageVector = avatar,
                contentDescription = null,
                tint = MuzzPink,
                modifier = Modifier.padding(2.dp)
            )
        }
        Text(
            text = name,
            style = MaterialTheme.typography.h1
        )
    }
}


@BasicPreview
@Composable
internal fun UserHeaderPreview() {
    ChattyTheme {
        UserHeader(
            name = "Sarah",
            avatar = Icons.Default.Person3
        )
    }
}