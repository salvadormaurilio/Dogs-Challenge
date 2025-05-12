package com.example.dogschallenge.data.datasource.local.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.dogschallenge.domain.model.Dog

@Entity(tableName = TABLE_DOG)
data class DogEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val name: String,
    val description: String,
    val age: Int,
    val image: String
)

fun Result<List<DogEntity>>.toDogs() = map { it.toDogs() }

private fun List<DogEntity>.toDogs() = map { it.toDog() }

private fun DogEntity.toDog() = Dog(
    name = name,
    description = description,
    age = age,
    image = image
)

fun Result<List<DogEntity>>.isValidDogs() = isSuccess && !getOrNull().isNullOrEmpty()


