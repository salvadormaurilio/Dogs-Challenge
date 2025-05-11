package com.example.dogschallenge.data.datasource.remote.retrofit

import com.google.gson.annotations.SerializedName

data class DogResponse(
    @SerializedName("dogName")
    val dogName: String?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("age")
    val age: Int?,
    @SerializedName("image")
    val image: String?
)
