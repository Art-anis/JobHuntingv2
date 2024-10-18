package com.nerazim.emtest.domain

class RemoveFromFavoritesUseCase(private val repository: DataRepository) {

    fun execute(vacancy: Vacancy) {
        repository.removeFavorite(vacancy)
    }
}