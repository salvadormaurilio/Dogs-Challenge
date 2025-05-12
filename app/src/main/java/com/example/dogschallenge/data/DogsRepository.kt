package com.example.dogschallenge.data

import com.example.dogschallenge.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface DogsRepository {

    fun getDogs(isRefresh: Boolean = false): Flow<Result<List<Dog>>>
}
