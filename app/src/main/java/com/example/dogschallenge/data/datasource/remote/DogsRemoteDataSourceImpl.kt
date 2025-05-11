package com.example.dogschallenge.data.datasource.remote

import com.example.dogschallenge.data.datasource.exception.DataException
import com.example.dogschallenge.data.datasource.remote.retrofit.DogResponse
import com.example.dogschallenge.data.datasource.remote.retrofit.DogsServiceRetrofit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DogsRemoteDataSourceImpl(
    private val dogsServiceRetrofit: DogsServiceRetrofit
) : DogsRemoteDataSource {

    override fun fetchDogs(): Flow<Result<List<DogResponse>>> = flow {
        try {
            val dogsResponse = dogsServiceRetrofit.fetchDogs()
            emit(Result.success(dogsResponse))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Result.failure(DataException.DogsException()))
        }
    }
}
