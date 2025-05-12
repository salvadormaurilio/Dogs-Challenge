package com.example.dogschallenge.data

import com.example.dogschallenge.data.datasource.local.DogsLocalDataSource
import com.example.dogschallenge.data.datasource.local.room.DogEntity
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

    override fun getDogs(isRefresh: Boolean) =
        if (isRefresh) fetchAndStoreDogs() else getOrFetchDogs()

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun getOrFetchDogs() = dogsLocalDataSource.getDogs()
        .flatMapLatest { dogsEntity -> emitDogsOrFetchDogs(dogsEntity) }

    private fun emitDogsOrFetchDogs(dogsEntity: Result<List<DogEntity>>) =
        if (dogsEntity.isValidDogs()) flowOf(dogsEntity.toDogs()) else fetchAndStoreDogs()

    private fun fetchAndStoreDogs() = dogsRemoteDataSource.fetchDogs()
        .map { dogsResponse -> dogsResponse.toDogs() }
        .onEach { dogs -> if (dogs.isSuccess) dogsLocalDataSource.insertDogs(dogs.toDogsEntity()) }

}
