package com.example.dogschallenge.ui.views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.dogschallenge.ui.theme.DogsChallengeTheme

@Composable
fun CircularProgressIndicatorFixMax(
    modifier: Modifier = Modifier,
    isVisible: Boolean = false
) {
    if (isVisible)
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center,
        ) {
            CircularProgressIndicator()
        }
}

@Preview(showBackground = true)
@Composable
fun CircularProgressIndicatorFixMaxPreview() {
    DogsChallengeTheme {
        CircularProgressIndicatorFixMax(
            isVisible = true
        )
    }
}

