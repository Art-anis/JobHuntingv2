package com.nerazim.emtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nerazim.emtest.domain.models.Vacancy
import com.nerazim.emtest.domain.usecases.GetFavoritesUseCase
import com.nerazim.emtest.domain.usecases.RemoveFromFavoritesUseCase

class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
): ViewModel() {

    private val favoriteListLiveMutable = MutableLiveData<List<Vacancy>>()
    private val favoriteListLive: LiveData<List<Vacancy>>
        get() = favoriteListLiveMutable

    fun getFavoritesListLive(): LiveData<List<Vacancy>> {
        refreshFavorites()
        return favoriteListLive
    }

    private fun refreshFavorites() {
        val newList = getFavoritesUseCase.execute()
        favoriteListLiveMutable.value = newList.map { it.copy() }
    }

    fun removeFromFavorites(vacancy: Vacancy) {
        removeFromFavoritesUseCase.execute(vacancy)
        refreshFavorites()
        //bbvm.refreshFavoriteNumber()
    }
}