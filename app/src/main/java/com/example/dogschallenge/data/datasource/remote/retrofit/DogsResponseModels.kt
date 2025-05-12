package com.example.dogschallenge.data.datasource.remote.retrofit

import com.example.dogschallenge.core.extensions.orDefault
import com.example.dogschallenge.domain.model.Dog
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

fun Result<List<DogResponse>>.toDogs() = map { it.toDogs() }

private fun List<DogResponse>.toDogs() = map { it.toDog() }

private fun DogResponse.toDog() = Dog(
    name = dogName.orEmpty(),
    description = description.orEmpty(),
    age = age.orDefault(),
    image = image.orEmpty()
)
