package com.nerazim.emtest.domain.usecases

import com.nerazim.emtest.domain.models.Vacancy
import com.nerazim.emtest.domain.repository.DataRepository

class GetFavoritesUseCase(private val repository: DataRepository) {
    fun execute(): List<Vacancy> {
        return repository.getVacancies().filter { it.isFavorite == true }
    }
}