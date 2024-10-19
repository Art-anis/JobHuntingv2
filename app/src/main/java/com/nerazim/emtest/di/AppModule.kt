package com.nerazim.emtest.di

import com.nerazim.emtest.viewmodels.FavoriteNumberViewModel
import com.nerazim.emtest.viewmodels.FavoritesViewModel
import com.nerazim.emtest.viewmodels.SearchViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

//модуль app - все viewmodel
val appModule = module {

    //viewmodel по количеству вакансий в избранном
    viewModel<FavoriteNumberViewModel> {
        FavoriteNumberViewModel(getFavoritesNumberUseCase = get())
    }

    //viewmodel по списку вакансий в избранном
    viewModel<FavoritesViewModel> {
        FavoritesViewModel(
            getFavoritesUseCase = get(),
            removeFromFavoritesUseCase = get()
        )
    }

    //viewmodel для рекомендаций и вакансий
    viewModel<SearchViewModel> {
        SearchViewModel(
            getOffersUseCase = get(),
            getVacanciesUseCase = get(),
            addToFavoritesUseCase = get(),
            removeFromFavoritesUseCase = get()
        )
    }
}