package com.example.dogschallenge.ui.views

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.dogschallenge.ui.DogsViewModel

@Composable
fun DogsScreen(
    viewModel: DogsViewModel = hiltViewModel(),
) {
    LaunchedEffect(Unit) {
        viewModel.initGetDogs()
    }
}






