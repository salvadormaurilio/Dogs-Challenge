package com.example.dogschallenge.data.datasource.local

import com.example.dogschallenge.data.datasource.exception.DataException
import com.example.dogschallenge.data.datasource.local.room.DogEntity
import com.example.dogschallenge.data.datasource.local.room.DogsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogsLocalDataSourceImpl(private val dogsDao: DogsDao) : DogsLocalDataSource {

    override suspend fun insertDogs(dogs: List<DogEntity>) {
        dogsDao.insertAll(dogs)
    }

    override fun getDogs(): Flow<Result<List<DogEntity>>> = flow {
        try {
            val dogsEntity = dogsDao.getAll()
            emit(Result.success(dogsEntity))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Result.failure(DataException.DogsException()))
        }
    }
}
