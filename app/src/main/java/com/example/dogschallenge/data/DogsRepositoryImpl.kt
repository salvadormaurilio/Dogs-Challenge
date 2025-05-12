package com.example.dogschallenge.data

import com.example.dogschallenge.data.datasource.local.DogsLocalDataSource
import com.example.dogschallenge.data.datasource.local.room.isValidDogs
import com.example.dogschallenge.data.datasource.local.room.toDogs
import com.example.dogschallenge.data.datasource.remote.DogsRemoteDataSource
import com.example.dogschallenge.data.datasource.remote.retrofit.toDogs
import com.example.dogschallenge.domain.model.toDogsEntity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class DogsRepositoryImpl(
    private val dogsRemoteDataSource: DogsRemoteDataSource,
    private val dogsLocalDataSource: DogsLocalDataSource
) : DogsRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getDogs() = dogsLocalDataSource.getDogs()
        .flatMapLatest { dogsEntity ->
            if (dogsEntity.isValidDogs())
                dogsRemoteDataSource.fetchDogs()
                    .map { dogsResponse -> dogsResponse.toDogs() }
                    .onEach { dogs -> if (dogs.isSuccess) dogsLocalDataSource.insertDogs(dogs.toDogsEntity()) }
            else
                flowOf(dogsEntity.toDogs())
        }
}
