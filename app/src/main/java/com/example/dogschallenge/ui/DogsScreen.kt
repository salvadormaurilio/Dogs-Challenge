package com.example.dogschallenge.ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.dogschallenge.R
import com.example.dogschallenge.core.extensions.empty
import com.example.dogschallenge.data.datasource.exception.DataException
import com.example.dogschallenge.domain.model.Dog
import com.example.dogschallenge.ui.theme.DogsChallengeTheme
import com.example.dogschallenge.ui.views.CircularProgressIndicatorFixMax
import com.example.dogschallenge.ui.views.DogItem
import com.example.dogschallenge.ui.views.DogsErrorScreen

@Composable
fun DogsScreen(
    viewModel: DogsViewModel = hiltViewModel(),
    onBackClick: () -> Unit = {}
) {

    val uiState = viewModel.dogsUiState.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.initGetDogs()
    }

    DogsContent(
        isLoading = uiState.value.isLoading,
        dogs = uiState.value.dogs,
        error = uiState.value.error,
        onRetry = viewModel::retryGetDogs,
        onBackClick = onBackClick
    )
}

@Composable
private fun DogsContent(
    isLoading: Boolean = false,
    dogs: List<Dog>? = null,
    error: Throwable? = null,
    onRetry: () -> Unit = {},
    onBackClick: () -> Unit = {},
) {
    Scaffold(
        topBar = {
            DogsTopAppBar(onBackClick = onBackClick)
        },
        content = { paddingValues ->
            Dogs(
                modifier = Modifier.padding(paddingValues),
                dogs = dogs
            )

            DogsErrorScreen(
                modifier = Modifier.padding(paddingValues),
                error = error,
                onRetry = onRetry
            )

            CircularProgressIndicatorFixMax(
                modifier = Modifier.padding(paddingValues),
                isVisible = isLoading
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DogsTopAppBar(onBackClick: () -> Unit = {}) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
        title = {
            Text(
                text = stringResource(id = R.string.app_name),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    tint = MaterialTheme.colorScheme.onBackground,
                    contentDescription = String.empty()
                )
            }
        }
    )
}

@Composable
fun Dogs(
    modifier: Modifier = Modifier,
    dogs: List<Dog>?
) {
    if (dogs == null) return

    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(vertical = 8.dp)
    ) {
        items(items = dogs) {
            DogItem(dog = it)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DogsContentUiStateLoadingPreview() {
    DogsChallengeTheme {
        DogsContent(
            isLoading = true
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DogsContentUiStateSuccessPreview() {
    DogsChallengeTheme {
        DogsContent(
            dogs = getDogsTestData()
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DogsContentUiStateErrorPreview() {
    DogsChallengeTheme {
        DogsContent(
            error = DataException.DogsException()
        )
    }
}
