package com.example.dogschallenge.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dogschallenge.core.coroutines.CoroutinesDispatchers
import com.example.dogschallenge.domain.GetDogsUseCase
import com.example.dogschallenge.domain.model.Dog
import com.example.dogschallenge.ui.model.DogsUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DogsViewModel @Inject constructor(
    private val getDogsUseCase: GetDogsUseCase,
    private val coroutinesDispatchers: CoroutinesDispatchers
) : ViewModel() {

    private var isRefresh = false
    private var jobGetDogs: Job? = null

    private val _dogsUiState = MutableStateFlow(DogsUiState())
    val dogsUiState = _dogsUiState.asStateFlow()

    fun initGetDogs() {
        if (dogsUiState.value.dogs != null) return
        getDogs()
    }

    fun retryGetDogs() {
        if (dogsUiState.value.isLoading) return
        getDogs()
    }

    fun refreshDogs() {
        if (dogsUiState.value.isLoading) return
        isRefresh = true
        getDogs()
    }

    private fun getDogs() {
        jobGetDogs?.cancel()
        jobGetDogs = viewModelScope.launch(coroutinesDispatchers.io) {
            updateDogsUiState(isLoading = true)
            getDogsUseCase(isRefresh = isRefresh).collect {
                getDogsSuccess(it)
                getDogsError(it)
            }
        }
    }

    private fun getDogsSuccess(result: Result<List<Dog>>) = result.onSuccess {
        updateDogsUiState(dogs = it)
        isRefresh = false
    }

    private fun getDogsError(result: Result<List<Dog>>) = result.onFailure {
        updateDogsUiState(dogs = null, error = it)
        it.printStackTrace()
    }

    private fun updateDogsUiState(
        isLoading: Boolean = false,
        dogs: List<Dog>? = dogsUiState.value.dogs,
        error: Throwable? = null
    ) {
        _dogsUiState.update {
            it.copy(
                isLoading = isLoading,
                dogs = dogs,
                error = error
            )
        }
    }
}
