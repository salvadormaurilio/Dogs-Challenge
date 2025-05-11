package com.example.dogschallenge.domain

import com.example.dogschallenge.data.datasource.DogsRepository

class GetDogsUseCaseImpl(private val dogsRepository: DogsRepository) : GetDogsUseCase {

    override operator fun invoke() = dogsRepository.getDogs()
}
