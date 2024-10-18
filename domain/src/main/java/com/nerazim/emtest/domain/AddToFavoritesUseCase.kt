package com.nerazim.emtest.domain

class AddToFavoritesUseCase(private val repository: DataRepository) {

    fun execute(vacancy: Vacancy) {
        repository.addFavorite(vacancy)
    }
}