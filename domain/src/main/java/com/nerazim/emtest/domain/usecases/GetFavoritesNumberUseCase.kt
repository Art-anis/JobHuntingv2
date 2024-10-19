package com.nerazim.emtest.domain.usecases

import com.nerazim.emtest.domain.repository.DataRepository

//usecase получение количества вакансий в избранном
class GetFavoritesNumberUseCase(private val repository: DataRepository) {
    //исполнение
    fun execute(): Int {
        return repository.getFavoritesNumber()
    }
}