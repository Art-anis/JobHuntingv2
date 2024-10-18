package com.nerazim.emtest.viewmodels

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.nerazim.emtest.app.jobApplication
import com.nerazim.emtest.domain.AddToFavoritesUseCase

object ViewModelFactory {
    val Factory = viewModelFactory {

        initializer {
            val getOffersUseCase =
                com.nerazim.emtest.domain.GetOffersUseCase(jobApplication().container.dataRepository)
            val getVacanciesUseCase =
                com.nerazim.emtest.domain.GetVacanciesUseCase(jobApplication().container.dataRepository)
            val addToFavoritesUseCase = AddToFavoritesUseCase(jobApplication().container.dataRepository)
            val removeFromFavoritesUseCase =
                com.nerazim.emtest.domain.RemoveFromFavoritesUseCase(jobApplication().container.dataRepository)
            SearchViewModel(
                getOffersUseCase = getOffersUseCase,
                getVacanciesUseCase = getVacanciesUseCase,
                addToFavoritesUseCase = addToFavoritesUseCase,
                removeFromFavoritesUseCase = removeFromFavoritesUseCase
            )
        }

        initializer {
            val getFavoritesUseCase =
                com.nerazim.emtest.domain.GetFavoritesUseCase(jobApplication().container.dataRepository)
            val removeFromFavoritesUseCase =
                com.nerazim.emtest.domain.RemoveFromFavoritesUseCase(jobApplication().container.dataRepository)
            FavoritesViewModel(
                getFavoritesUseCase = getFavoritesUseCase,
                removeFromFavoritesUseCase = removeFromFavoritesUseCase
            )
        }

        initializer {
            val getFavoritesNumberUseCase =
                com.nerazim.emtest.domain.GetFavoritesNumberUseCase(jobApplication().container.dataRepository)
            FavoriteNumberViewModel(
                getFavoritesNumberUseCase = getFavoritesNumberUseCase
            )
        }
    }
}