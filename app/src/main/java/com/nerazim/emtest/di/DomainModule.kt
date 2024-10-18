package com.nerazim.emtest.di

import com.nerazim.emtest.domain.usecases.AddToFavoritesUseCase
import com.nerazim.emtest.domain.usecases.GetFavoritesNumberUseCase
import com.nerazim.emtest.domain.usecases.GetFavoritesUseCase
import com.nerazim.emtest.domain.usecases.GetOffersUseCase
import com.nerazim.emtest.domain.usecases.GetVacanciesUseCase
import com.nerazim.emtest.domain.usecases.RemoveFromFavoritesUseCase
import org.koin.dsl.module

//модуль домена - создание юзкейсов
val domainModule = module {

    //всем юзкейсам репозиторий поступает через коин

    //добавить вакансию в избранное
    factory<AddToFavoritesUseCase> {
        AddToFavoritesUseCase(repository = get())
    }

    //получить все вакансии в избранном
    factory<GetFavoritesUseCase> {
        GetFavoritesUseCase(repository = get())
    }

    //получить количество вакансий в избранном
    factory<GetFavoritesNumberUseCase> {
        GetFavoritesNumberUseCase(repository = get())
    }

    //получить все рекомендации
    factory<GetOffersUseCase> {
        GetOffersUseCase(repository = get())
    }

    //получить все вакансии
    factory<GetVacanciesUseCase> {
        GetVacanciesUseCase(repository = get())
    }

    //убрать вакансию из избранного
    factory<RemoveFromFavoritesUseCase> {
        RemoveFromFavoritesUseCase(repository = get())
    }
}