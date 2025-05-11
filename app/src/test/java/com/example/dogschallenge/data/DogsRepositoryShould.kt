package com.example.dogschallenge.data

import com.example.dogschallenge.core.assertThatEquals
import com.example.dogschallenge.core.assertThatIsInstanceOf
import com.example.dogschallenge.data.datasource.exception.DataException
import com.example.dogschallenge.data.datasource.remote.DogsRemoteDataSource
import com.example.dogschallenge.data.datasource.remote.retrofit.DogResponse
import com.example.dogschallenge.fakedata.givenDogsFakeData
import com.example.dogschallenge.fakedata.givenDogsResponseFakeData
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DogsRepositoryShould {

    private val dogsRemoteDataSource = mock<DogsRemoteDataSource>()
    private lateinit var dogsRepository: DogsRepository

    @Before
    fun setup() {
        dogsRepository = DogsRepositoryImpl(dogsRemoteDataSource)
    }

    @Test
    fun `Get Dogs data when getDogs is success`() = runTest {
        val dogsResponse = givenDogsResponseFakeData()
        val dogs = givenDogsFakeData()
        val resultSuccess = Result.success(dogsResponse)
        whenever(dogsRemoteDataSource.fetchDogs()).thenReturn(flowOf(resultSuccess))

        val result = dogsRepository.getDogs().lastOrNull()

        verify(dogsRemoteDataSource).fetchDogs()
        assertThatEquals(result?.getOrNull(), dogs)
    }

    @Test
    fun `Get DogsException data when getDogs is failure`() = runTest {
        val resultFailure: Result<List<DogResponse>> = Result.failure(DataException.DogsException())
        whenever(dogsRemoteDataSource.fetchDogs()).thenReturn(flowOf(resultFailure))

        val result = dogsRepository.getDogs().lastOrNull()

        verify(dogsRemoteDataSource).fetchDogs()
        assertThatIsInstanceOf<DataException.DogsException>(result?.exceptionOrNull())
    }
}
