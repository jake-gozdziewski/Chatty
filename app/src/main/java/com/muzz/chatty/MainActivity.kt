package com.muzz.chatty

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.core.view.WindowCompat
import com.muzz.chatty.ui.ChatViewModel
import com.muzz.chatty.ui.MainScreen
import com.muzz.chatty.ui.theme.ChattyTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: ChatViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ChattyTheme {
                MainScreen(
                    viewModel = viewModel
                )
            }
        }
    }
}