package com.nerazim.emtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nerazim.emtest.domain.Vacancy
import com.nerazim.emtest.domain.GetFavoritesUseCase
import com.nerazim.emtest.domain.RemoveFromFavoritesUseCase

class FavoritesViewModel(
    private val getFavoritesUseCase: com.nerazim.emtest.domain.GetFavoritesUseCase,
    private val removeFromFavoritesUseCase: com.nerazim.emtest.domain.RemoveFromFavoritesUseCase
): ViewModel() {

    private val favoriteListLiveMutable = MutableLiveData<List<com.nerazim.emtest.domain.Vacancy>>()
    private val favoriteListLive: LiveData<List<com.nerazim.emtest.domain.Vacancy>>
        get() = favoriteListLiveMutable

    fun getFavoritesListLive(): LiveData<List<com.nerazim.emtest.domain.Vacancy>> {
        refreshFavorites()
        return favoriteListLive
    }

    private fun refreshFavorites() {
        val newList = getFavoritesUseCase.execute()
        favoriteListLiveMutable.value = newList.map { it.copy() }
    }

    fun removeFromFavorites(vacancy: com.nerazim.emtest.domain.Vacancy) {
        removeFromFavoritesUseCase.execute(vacancy)
        refreshFavorites()
        //bbvm.refreshFavoriteNumber()
    }
}