package com.muzz.chatty.ui.chat

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DoneAll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.muzz.chatty.ui.theme.*


internal val BubbleShape = RoundedCornerShape(size = 16.dp)
internal val TailedMessageShape = BubbleShape.copy(bottomEnd = CornerSize(0.dp))
internal val TailedReplyShape = BubbleShape.copy(bottomStart = CornerSize(0.dp))

/**
 * Container for the message with given [text] sent **from** the current user.
 * If [showTail] is true, the bottom left corner is squared.
 */
@Composable
fun MessageChip(
    text: String,
    modifier: Modifier = Modifier,
    showTail: Boolean = false,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterEnd,
    ) {
        Surface(
            modifier = Modifier.widthIn(max = 300.dp),
            shape = if (showTail) TailedMessageShape else BubbleShape,
            color = MuzzPink
        ) {
            Box {
                Text(
                    text = text,
                    style = MaterialTheme.typography.body1,
                    modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
                )
                Icon(
                    imageVector = Icons.Default.DoneAll,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .size(14.dp)
                        .offset(x = (-6).dp, y = (-2).dp),
                    tint = MuzzYellow
                )
            }
            
        }
    }
}


@BasicPreview
@Composable
internal fun MessageChipPreview() {
    ChattyTheme {
        MessageChip(
            text = """
                Does 7pm work for you? I've got to go pick up my little brother first from a party
            """.trimIndent()
        )
    }
}



/**
 * Container for the message with given [text] sent **to** the current user
 * If [showTail] is true, the bottom right corner is squared.
 */
@Composable
fun ReplyChip(
    text: String,
    modifier: Modifier = Modifier,
    showTail: Boolean = false,
) {
    Box(
        modifier = modifier.fillMaxWidth(),
        contentAlignment = Alignment.CenterStart,
    ) {
        Surface(
            modifier = Modifier.widthIn(max = 300.dp),
            shape = if (showTail) TailedReplyShape else BubbleShape,
            color = MuzzSnow
        ) {
            Text(
                text = text,
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 16.dp)
            )
        }
    }
}

@BasicPreview
@Composable
internal fun ReplyChipPreview() {
    ChattyTheme {
        ReplyChip(
            text = """
                Does 7pm work for you? I've got to go pick up my little brother first from a party
            """.trimIndent()
        )
    }
}



/** Simple header for a group of messages, [text] expected in form "day hh:mm" */
@Composable
fun ChatHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    val (day, time) = text.split(" ") // Input assumed as "day hh:mm"
    
    Row(
        modifier = modifier, 
        horizontalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = day,
            style = MaterialTheme.typography.caption,
            color = Color.Black.copy(alpha = 0.4f), // Would need to change for dynamic themes
            fontWeight = FontWeight.W800,
        )
        Text(
            text = time,
            style = MaterialTheme.typography.caption,
            color = Color.Black.copy(alpha = 0.4f), // Would need to change for dynamic themes
        )
    }
}


@BasicPreview
@Composable
internal fun ChatHeaderPreview() {
    ChattyTheme {
        ChatHeader(text = "Thursday 22:33")
    }
}