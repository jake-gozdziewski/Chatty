package com.muzz.chatty.ui.chat

import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronLeft
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.dp
import com.muzz.chatty.ui.theme.BasicPreview
import com.muzz.chatty.ui.theme.ChattyTheme
import com.muzz.chatty.ui.theme.MuzzPink
import com.muzz.chatty.ui.theme.MuzzYellow

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