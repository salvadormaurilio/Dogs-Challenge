package com.example.dogschallenge.domain

import com.example.dogschallenge.data.datasource.DogsRepository
import javax.inject.Inject

class GetDogsUseCaseImpl @Inject constructor(
    private val dogsRepository: DogsRepository
) : GetDogsUseCase {

    override operator fun invoke() = dogsRepository.getDogs()
}
