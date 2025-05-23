package com.example.dogschallenge.domain

import com.example.dogschallenge.data.DogsRepository

class GetDogsUseCaseImpl(private val dogsRepository: DogsRepository) : GetDogsUseCase {

    override operator fun invoke(isRefresh: Boolean) = dogsRepository.getDogs(isRefresh)
}
