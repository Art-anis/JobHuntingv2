package com.nerazim.emtest.domain.models

//общий объект, получаемый из JSON
data class Data(
    val offers: List<Offer> = listOf(), //список рекомендаций
    val vacancies: List<Vacancy> = listOf(), //список вакансий
    var favoriteNumber: Int = 0 //количество вакансий в "Избранном"
)
