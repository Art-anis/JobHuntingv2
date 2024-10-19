package com.nerazim.emtest.domain.usecases

import com.nerazim.emtest.domain.models.Vacancy
import com.nerazim.emtest.domain.repository.DataRepository

//usecase получения вакансий
class GetVacanciesUseCase(private val repository: DataRepository) {
    //исполнение
    fun execute(): List<Vacancy> {
        return repository.getVacancies()
    }
}