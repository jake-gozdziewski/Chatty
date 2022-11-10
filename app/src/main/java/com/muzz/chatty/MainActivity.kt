package com.muzz.chatty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.muzz.chatty.ui.MainScreen
import com.muzz.chatty.ui.theme.ChattyTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChattyTheme {
                MainScreen()
            }
        }
    }
}