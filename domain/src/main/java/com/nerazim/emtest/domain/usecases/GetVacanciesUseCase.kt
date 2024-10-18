package com.nerazim.emtest.domain.usecases

import com.nerazim.emtest.domain.models.Vacancy
import com.nerazim.emtest.domain.repository.DataRepository

class GetVacanciesUseCase(private val repository: DataRepository) {

    fun execute(): List<Vacancy> {
        return repository.getVacancies()
    }
}