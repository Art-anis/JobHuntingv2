package com.nerazim.emtest.domain.usecases

import com.nerazim.emtest.domain.models.Vacancy
import com.nerazim.emtest.domain.repository.DataRepository

//usecase добавление вакансии в избранное
class AddToFavoritesUseCase(private val repository: DataRepository) {
    //исполнение
    fun execute(vacancy: Vacancy) {
        repository.addFavorite(vacancy)
    }
}