package com.nerazim.emtest.domain

class GetVacanciesUseCase(private val repository: DataRepository) {

    fun execute(): List<Vacancy> {
        return repository.getVacancies()
    }
}