package com.nerazim.emtest.domain

class GetFavoritesNumberUseCase(private val repository: DataRepository) {
    fun execute(): Int {
        return repository.getFavoritesNumber()
    }
}