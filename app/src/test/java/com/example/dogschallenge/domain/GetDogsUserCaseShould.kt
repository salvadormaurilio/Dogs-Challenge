package com.example.dogschallenge.domain

import com.example.dogschallenge.core.assertThatEquals
import com.example.dogschallenge.core.assertThatIsInstanceOf
import com.example.dogschallenge.data.DogsRepository
import com.example.dogschallenge.data.datasource.exception.DataException
import com.example.dogschallenge.domain.model.Dog
import com.example.dogschallenge.fakedata.givenDogsFakeData
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class GetDogsUserCaseShould {

    private val dogsRepository = mock<DogsRepository>()
    private lateinit var getDogsUseCase: GetDogsUseCase

    @Before
    fun setup() {
        getDogsUseCase = GetDogsUseCaseImpl(dogsRepository)
    }

    @Test
    fun `Get Dogs data when getDogs is success`() = runTest {
        val dogs = givenDogsFakeData()
        val resultSuccess = Result.success(dogs)
        whenever(dogsRepository.getDogs()).thenReturn(flowOf(resultSuccess))

        val result = getDogsUseCase().lastOrNull()

        verify(dogsRepository).getDogs()
        assertThatEquals(result?.getOrNull(), dogs)
    }

    @Test
    fun `Get DogsException data when getDogs is failure`() = runTest {
        val resultFailure: Result<List<Dog>> = Result.failure(DataException.DogsException())
        whenever(dogsRepository.getDogs()).thenReturn(flowOf(resultFailure))

        val result = getDogsUseCase().lastOrNull()

        verify(dogsRepository).getDogs()
        assertThatIsInstanceOf<DataException.DogsException>(result?.exceptionOrNull())
    }
}
