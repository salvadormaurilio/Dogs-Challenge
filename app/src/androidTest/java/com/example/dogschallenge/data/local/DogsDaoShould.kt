package com.example.dogschallenge.data.local

import com.example.dogschallenge.core.RoomRule
import com.example.dogschallenge.core.assertThatEquals
import com.example.dogschallenge.data.datasource.local.room.DogsDao
import com.example.dogschallenge.data.datasource.local.room.DogsRoomDatabase
import com.example.dogschallenge.fakedata.givenDogsEntityFakeData
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class DogsDaoShould {

    @get:Rule
    val userRoomDatabase = RoomRule.build<DogsRoomDatabase>()

    private lateinit var dogsDao: DogsDao

    @Before
    fun setUp() {
        dogsDao = userRoomDatabase.database().dogsDao()
    }

    @Test
    fun insertDogsEntityInDatabase() = runTest {
        val dogsEntity = givenDogsEntityFakeData()

        dogsDao.insert(dogsEntity)
        val dogsEntityResult = dogsDao.getAll()

        assertThatEquals(dogsEntityResult, dogsEntity)
    }

    @Test
    fun replaceDogsEntityInDatabaseWhenInsertIsCalledMultipleTimes() = runTest {
        val dogsEntity = givenDogsEntityFakeData()

        dogsDao.insert(dogsEntity)
        dogsDao.insert(dogsEntity)
        val dogsEntityResult = dogsDao.getAll()

        assertThatEquals(dogsEntityResult, dogsEntity)
    }
}
