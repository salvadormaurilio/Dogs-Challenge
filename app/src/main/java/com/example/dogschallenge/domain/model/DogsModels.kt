package com.example.dogschallenge.domain.model

import com.example.dogschallenge.core.extensions.orDefault
import com.example.dogschallenge.data.datasource.model.DogResponse

data class Dog(
    val name: String,
    val description: String,
    val age: Int,
    val image: String
)

fun Result<List<DogResponse>>.toDogs() = map { it.toDogs() }

fun List<DogResponse>.toDogs() = map { it.toDog() }

private fun DogResponse.toDog() = Dog(
    name = dogName.orEmpty(),
    description = description.orEmpty(),
    age = age.orDefault(),
    image = image.orEmpty()
)
