package com.example.dogschallenge.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.dogschallenge.ui.theme.DogsChallengeTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DogsActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DogsChallengeTheme {
                DogsScreen(
                    onBackClick = { onBackPressedDispatcher.onBackPressed() }
                )
            }
        }
    }
}

