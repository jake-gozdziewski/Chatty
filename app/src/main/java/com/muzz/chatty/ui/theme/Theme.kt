package com.muzz.chatty.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

@Composable
fun ChattyTheme(content: @Composable () -> Unit) {
    MaterialTheme(
        colors = lightColors(primary = MuzzPink),
        typography = Typography,
        content = content
    )
}