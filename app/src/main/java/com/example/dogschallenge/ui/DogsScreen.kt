package com.example.dogschallenge.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun DogsScreen(
    viewModel: DogsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.initGetDogs()
    }
}
