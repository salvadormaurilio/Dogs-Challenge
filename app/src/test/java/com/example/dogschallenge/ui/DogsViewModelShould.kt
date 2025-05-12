package com.example.dogschallenge.ui

import com.example.dogschallenge.core.TestDispatcherRule
import com.example.dogschallenge.core.assertThatEquals
import com.example.dogschallenge.core.assertThatIsInstanceOf
import com.example.dogschallenge.data.datasource.exception.DataException
import com.example.dogschallenge.domain.GetDogsUseCase
import com.example.dogschallenge.fakedata.givenDogsFakeData
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.whenever

class DogsViewModelShould {

    @get:Rule
    var testDispatcherRule = TestDispatcherRule()

    private val getDogsUseCase = mock<GetDogsUseCase>()
    private lateinit var dogsViewModel: DogsViewModel

    @Before
    fun setup() {
        dogsViewModel = DogsViewModel(getDogsUseCase, testDispatcherRule.coroutinesDispatchers)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Get Dogs data when initGetDogs is called and getDogs is success`() = runTest {
        val dogs = givenDogsFakeData()
        whenever(getDogsUseCase()).thenReturn(flowOf(Result.success(dogs)))

        dogsViewModel.initGetDogs()
        advanceUntilIdle()

        val result = dogsViewModel.dogsUiState.firstOrNull()

        verify(getDogsUseCase)()
        assertThatEquals(result?.dogs, dogs)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Get DogsException data when initGetDogs is called and getDogs is failure`() = runTest {
        whenever(getDogsUseCase()).thenReturn(flowOf(Result.failure(DataException.DogsException())))

        dogsViewModel.initGetDogs()
        advanceUntilIdle()

        val result = dogsViewModel.dogsUiState.firstOrNull()

        verify(getDogsUseCase)()
        assertThatIsInstanceOf<DataException.DogsException>(result?.error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Get Dogs data when retryGetDogs is called and getDogs is success`() = runTest {
        val dogs = givenDogsFakeData()
        whenever(getDogsUseCase()).thenReturn(flowOf(Result.success(dogs)))

        dogsViewModel.retryGetDogs()
        advanceUntilIdle()

        val result = dogsViewModel.dogsUiState.firstOrNull()

        verify(getDogsUseCase)()
        assertThatEquals(result?.dogs, dogs)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Get DogsException data when retryGetDogs is called and getDogs is failure`() = runTest {
        whenever(getDogsUseCase()).thenReturn(flowOf(Result.failure(DataException.DogsException())))

        dogsViewModel.retryGetDogs()
        advanceUntilIdle()

        val result = dogsViewModel.dogsUiState.firstOrNull()

        verify(getDogsUseCase)()
        assertThatIsInstanceOf<DataException.DogsException>(result?.error)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Get Dogs data when refreshDogs is called and getDogs is success`() = runTest {
        val dogs = givenDogsFakeData()
        whenever(getDogsUseCase(isRefresh = true)).thenReturn(flowOf(Result.success(dogs)))

        dogsViewModel.refreshDogs()
        advanceUntilIdle()

        val result = dogsViewModel.dogsUiState.firstOrNull()

        verify(getDogsUseCase)(isRefresh = true)
        assertThatEquals(result?.dogs, dogs)
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    @Test
    fun `Get DogsException data when refreshDogs is called and getDogs is failure`() = runTest {
        whenever(getDogsUseCase(isRefresh = true)).thenReturn(flowOf(Result.failure(DataException.DogsException())))

        dogsViewModel.refreshDogs()
        advanceUntilIdle()

        val result = dogsViewModel.dogsUiState.firstOrNull()

        verify(getDogsUseCase)(isRefresh = true)
        assertThatIsInstanceOf<DataException.DogsException>(result?.error)
    }
}
