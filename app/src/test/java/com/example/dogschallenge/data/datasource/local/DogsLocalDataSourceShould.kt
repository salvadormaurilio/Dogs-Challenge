package com.example.dogschallenge.data.datasource.local

import com.example.dogschallenge.core.assertThatEquals
import com.example.dogschallenge.core.assertThatIsInstanceOf
import com.example.dogschallenge.data.datasource.exception.DataException
import com.example.dogschallenge.data.datasource.local.room.DogsDao
import com.example.dogschallenge.fakedata.givenDogsEntityFakeData
import kotlinx.coroutines.flow.lastOrNull
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class DogsLocalDataSourceShould {

    private val userDao = mock<DogsDao>()

    private lateinit var dogsLocalDataSource: DogsLocalDataSource

    @Before
    fun setup() {
        dogsLocalDataSource = DogsLocalDataSourceImpl(userDao)
    }

    @Test
    fun `Call insertAll when insertDogs is called`() = runTest {
        val dogsEntity = givenDogsEntityFakeData()
        dogsLocalDataSource.insertDogs(dogsEntity)

        verify(userDao).insertAll(dogsEntity)
    }

    @Test
    fun `Get DogsEntity when getAll is success`() = runTest {
        val dogsEntity = givenDogsEntityFakeData()
        whenever(userDao.getAll()).thenReturn(dogsEntity)

        val result = dogsLocalDataSource.getDogs().lastOrNull()

        verify(userDao).getAll()
        assertThatEquals(result?.getOrNull(), dogsEntity)
    }

    @Test
    fun `Get DogsException when getAll is failure`() = runTest {
        whenever(userDao.getAll()).thenThrow(RuntimeException())

        val result = dogsLocalDataSource.getDogs().lastOrNull()

        verify(userDao).getAll()
        assertThatIsInstanceOf<DataException.DogsException>(result?.exceptionOrNull())
    }
}
