package com.example.dogschallenge.ui.model

import com.example.dogschallenge.domain.model.Dog

data class DogsUiState(
    val isLoading: Boolean = false,
    val dogs: List<Dog>? = null,
    val error: Throwable? = null
)
