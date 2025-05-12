package com.example.dogschallenge.domain.model

import com.example.dogschallenge.data.datasource.local.room.DogEntity

data class Dog(
    val name: String,
    val description: String,
    val age: Int,
    val image: String
)

fun Result<List<Dog>>.toDogsEntity() = getOrNull()?.toDogsEntity().orEmpty()

private fun List<Dog>.toDogsEntity() = map { it.toDogEntity() }

private fun Dog.toDogEntity() = DogEntity(
    name = name,
    description = description,
    age = age,
    image = image
)
