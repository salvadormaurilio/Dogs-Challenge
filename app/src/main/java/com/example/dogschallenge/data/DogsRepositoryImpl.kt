package com.example.dogschallenge.data

import com.example.dogschallenge.data.datasource.DogsRepository
import com.example.dogschallenge.data.datasource.remote.DogsRemoteDataSource
import com.example.dogschallenge.domain.model.toDogs
import kotlinx.coroutines.flow.map

class DogsRepositoryImpl(private val dogsRemoteDataSource: DogsRemoteDataSource) : DogsRepository {

    override fun getDogs() = dogsRemoteDataSource.fetchDogs().map { it.toDogs() }
}
