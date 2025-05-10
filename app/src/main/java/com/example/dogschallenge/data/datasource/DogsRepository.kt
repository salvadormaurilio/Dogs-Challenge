package com.example.dogschallenge.data.datasource

import com.example.dogschallenge.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {

    fun getDogs(): Flow<Result<List<Dog>>>
}
