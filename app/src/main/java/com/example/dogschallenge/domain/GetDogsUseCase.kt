package com.example.dogschallenge.domain

import com.example.dogschallenge.domain.model.Dog
import kotlinx.coroutines.flow.Flow

interface GetDogsUseCase {

    operator fun invoke(isRefresh: Boolean = false): Flow<Result<List<Dog>>>
}
