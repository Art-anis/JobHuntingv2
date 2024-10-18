package com.nerazim.emtest.domain

class GetFavoritesUseCase(private val repository: DataRepository) {
    fun execute(): List<Vacancy> {
        return repository.getVacancies().filter { it.isFavorite == true }
    }
}