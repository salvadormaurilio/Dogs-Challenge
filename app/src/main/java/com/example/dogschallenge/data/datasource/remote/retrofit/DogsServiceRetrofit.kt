package com.example.dogschallenge.data.datasource.remote.retrofit

import com.example.dogschallenge.data.datasource.model.DogResponse
import retrofit2.http.GET

interface DogsServiceRetrofit {

    @GET(DOGS_ENDPOINT)
    suspend fun fetchDogs(): DogResponse
}
