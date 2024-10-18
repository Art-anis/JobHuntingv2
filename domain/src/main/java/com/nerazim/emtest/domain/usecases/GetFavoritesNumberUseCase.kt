package com.nerazim.emtest.domain.usecases

import com.nerazim.emtest.domain.repository.DataRepository

class GetFavoritesNumberUseCase(private val repository: DataRepository) {
    fun execute(): Int {
        return repository.getFavoritesNumber()
    }
}