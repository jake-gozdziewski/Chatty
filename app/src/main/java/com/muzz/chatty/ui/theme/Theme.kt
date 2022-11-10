package com.muzz.chatty.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
fun ChattyTheme(content: @Composable () -> Unit) {

    val systemUiController = rememberSystemUiController()
    systemUiController.setSystemBarsColor(color = Color.White, darkIcons = true)

    MaterialTheme(
        colors = lightColors(primary = MuzzPink),
        typography = Typography,
        content = content
    )
}