package com.example.dogschallenge.data.datasource.remote.retrofit

import retrofit2.http.GET

interface DogsServiceRetrofit {

    @GET(DOGS_ENDPOINT)
    suspend fun fetchDogs(): List<DogResponse>
}
