package com.example.dogschallenge.data.datasource.local

import com.example.dogschallenge.data.datasource.local.room.DogEntity
import kotlinx.coroutines.flow.Flow

interface DogsLocalDataSource {

    suspend fun insertDogs(dogs: List<DogEntity>)

    fun getDogs(): Flow<Result<List<DogEntity>>>
}
