package com.example.dogschallenge.data.datasource.remote

import com.example.dogschallenge.data.datasource.remote.retrofit.DogResponse
import kotlinx.coroutines.flow.Flow

interface DogsRemoteDataSource {

    fun fetchDogs(): Flow<Result<List<DogResponse>>>
}
