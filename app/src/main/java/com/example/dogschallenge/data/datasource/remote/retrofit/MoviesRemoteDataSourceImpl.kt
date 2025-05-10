package com.example.dogschallenge.data.datasource.remote.retrofit

import com.example.dogschallenge.data.datasource.model.DogResponse
import com.example.dogschallenge.data.datasource.remote.DogsRemoteDataSource
import com.example.dogschallenge.data.datasource.remote.exception.DataException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DogsRemoteDataSourceImpl @Inject constructor(private val dogsServiceRetrofit: DogsServiceRetrofit) :
    DogsRemoteDataSource {

    override fun fetchDogs(): Flow<Result<DogResponse>> = flow {
        try {
            val moviesResponse = dogsServiceRetrofit.fetchDogs()
            emit(Result.success(moviesResponse))
        } catch (exception: Exception) {
            exception.printStackTrace()
            emit(Result.failure(DataException.DogsException()))
        }
    }
}
