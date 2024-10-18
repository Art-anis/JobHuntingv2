package com.nerazim.emtest.domain.models

//вакансия
data class Vacancy(
    val id: String? = null, //идентификатор
    val lookingNumber: Int? = null, //сколько людей смотрит вакансию
    val title: String? = null, //название
    val address: Address? = null, //адрес компании
    val company: String? = null, //название компании
    val experience: Experience? = null, //опыт работы
    val publishedDate: String? = null, //дата публикации
    var isFavorite: Boolean? = null, //добавлена ли вакансия с избранное
    val salary: Salary? = null, //зарплата
    val schedules: List<String> = listOf(), //варианты занятости
    val appliedNumber: Int? = null, //число откликнувшихся
    val description: String? = null, //описание вакансии
    val responsibilities: String? = null, //ответственности
    val questions: List<String> = listOf() //список вопросов
)

//адрес компании
data class Address(
    val town: String? = null, //город
    val street: String? = null, //улица
    val house: String? = null //дом
)

//опыт работы
data class Experience(
    val previewText: String? = null, //кратко об опыте
    val text: String? = null //полная строка
)

//зарплата
data class Salary(
    val short: String? = null, //кратко о зарплате
    val full: String? = null //полная строка
)