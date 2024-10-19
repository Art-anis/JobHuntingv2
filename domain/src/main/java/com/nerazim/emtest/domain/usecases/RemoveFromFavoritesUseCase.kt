package com.nerazim.emtest.domain.usecases

import com.nerazim.emtest.domain.models.Vacancy
import com.nerazim.emtest.domain.repository.DataRepository

//usecase удаления вакансий из избранного
class RemoveFromFavoritesUseCase(private val repository: DataRepository) {

    //исполнение
    fun execute(vacancy: Vacancy) {
        repository.removeFavorite(vacancy)
    }
}