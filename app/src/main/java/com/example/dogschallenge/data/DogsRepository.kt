package com.example.dogschallenge.data

import com.example.dogschallenge.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {

    fun getDogs(): Flow<Result<List<Dog>>>
}
