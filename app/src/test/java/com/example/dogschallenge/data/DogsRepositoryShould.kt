package com.example.dogschallenge.data

import com.example.dogschallenge.core.assertThatEquals
import com.example.dogschallenge.core.assertThatIsInstanceOf
import com.example.dogschallenge.data.datasource.exception.DataException
import com.example.dogschallenge.data.datasource.local.DogsLocalDataSource
import com.example.dogschallenge.data.datasource.local.room.DogEntity
import com.example.dogschallenge.data.datasource.remote.DogsRemoteDataSource
import com.example.dogschallenge.data.datasource.remote.retrofit.DogResponse
import com.example.dogschallenge.fakedata.givenDogsEntityFakeData
import com.example.dogschallenge.fakedata.givenDogsFakeData
import com.example.dogschallenge.fakedata.givenDogsResponseFakeData
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.kotlin.any
import org.mockito.kotlin.never
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DogsRepositoryShould {

    private val dogsRemoteDataSource = mock<DogsRemoteDataSource>()
    private val dogsLocalDataSource = mock<DogsLocalDataSource>()
    private lateinit var dogsRepository: DogsRepository

    @Before
    fun setup() {
        dogsRepository = DogsRepositoryImpl(dogsRemoteDataSource, dogsLocalDataSource)
    }

    @Test
    fun `Get Dogs data when getDogs form local is success`() = runTest {
        val dogsEntity = givenDogsEntityFakeData()
        val dogs = givenDogsFakeData()
        val resultDogsEntitySuccess = Result.success(dogsEntity)
        whenever(dogsLocalDataSource.getDogs()).thenReturn(flowOf(resultDogsEntitySuccess))

        val result = dogsRepository.getDogs().lastOrNull()

        verify(dogsLocalDataSource).getDogs()
        verify(dogsRemoteDataSource, never()).fetchDogs()
        verify(dogsLocalDataSource, never()).insertDogs(any())
        assertThatEquals(result?.getOrNull(), dogs)
    }

    @Test
    fun `Get Dogs data when getDogs form local is empty and fetchDogs form remote is success`() =
        runTest {
            val dogsResponse = givenDogsResponseFakeData()
            val dogs = givenDogsFakeData()
            val dogsEntity = givenDogsEntityFakeData()
            val resultDogsEntityEmptySuccess: Result<List<DogEntity>> = Result.success(emptyList())
            val resultDogsResponseSuccess = Result.success(dogsResponse)
            whenever(dogsLocalDataSource.getDogs()).thenReturn(flowOf(resultDogsEntityEmptySuccess))
            whenever(dogsRemoteDataSource.fetchDogs()).thenReturn(flowOf(resultDogsResponseSuccess))

            val result = dogsRepository.getDogs().lastOrNull()

            verify(dogsLocalDataSource).getDogs()
            verify(dogsRemoteDataSource).fetchDogs()
            verify(dogsLocalDataSource).insertDogs(dogsEntity)
            assertThatEquals(result?.getOrNull(), dogs)
        }

    @Test
    fun `Get Dogs data when getDogs form local is failure and fetchDogs form remote is success`() =
        runTest {
            val dogsResponse = givenDogsResponseFakeData()
            val dogs = givenDogsFakeData()
            val dogsEntity = givenDogsEntityFakeData()
            val resultDogsEntityFailure: Result<List<DogEntity>> =
                Result.failure(DataException.DogsException())
            val resultDogsResponseSuccess = Result.success(dogsResponse)
            whenever(dogsLocalDataSource.getDogs()).thenReturn(flowOf(resultDogsEntityFailure))
            whenever(dogsRemoteDataSource.fetchDogs()).thenReturn(flowOf(resultDogsResponseSuccess))

            val result = dogsRepository.getDogs().lastOrNull()

            verify(dogsLocalDataSource).getDogs()
            verify(dogsRemoteDataSource).fetchDogs()
            verify(dogsLocalDataSource).insertDogs(dogsEntity)
            assertThatEquals(result?.getOrNull(), dogs)
        }

    @Test
    fun `Get Dogs data when getDogs form local is failure and fetchDogs form remote is failure`() =
        runTest {
            val resultDogsEntityFailure: Result<List<DogEntity>> =
                Result.failure(DataException.DogsException())
            val resultDogsResponseFailure: Result<List<DogResponse>> =
                Result.failure(DataException.DogsException())
            whenever(dogsLocalDataSource.getDogs()).thenReturn(flowOf(resultDogsEntityFailure))
            whenever(dogsRemoteDataSource.fetchDogs()).thenReturn(flowOf(resultDogsResponseFailure))

            val result = dogsRepository.getDogs().lastOrNull()

            verify(dogsLocalDataSource).getDogs()
            verify(dogsRemoteDataSource).fetchDogs()
            verify(dogsLocalDataSource, never()).insertDogs(any())
            assertThatIsInstanceOf<DataException.DogsException>(result?.exceptionOrNull())
        }
}
