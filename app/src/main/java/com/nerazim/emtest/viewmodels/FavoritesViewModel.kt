package com.nerazim.emtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nerazim.emtest.domain.models.Vacancy
import com.nerazim.emtest.domain.usecases.GetFavoritesUseCase
import com.nerazim.emtest.domain.usecases.RemoveFromFavoritesUseCase

//viewmodel для вакансий в избранном
class FavoritesViewModel(
    private val getFavoritesUseCase: GetFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
): ViewModel() {

    //livedata
    private val favoriteListLiveMutable = MutableLiveData<List<Vacancy>>()
    private val favoriteListLive: LiveData<List<Vacancy>>
        get() = favoriteListLiveMutable

    //получение livedata
    fun getFavoritesListLive(): LiveData<List<Vacancy>> {
        refreshFavorites()
        return favoriteListLive
    }

    //обновление livedata
    private fun refreshFavorites() {
        val newList = getFavoritesUseCase.execute()
        favoriteListLiveMutable.value = newList.map { it.copy() }
    }

    //удаление из избранного
    fun removeFromFavorites(vacancy: Vacancy) {
        removeFromFavoritesUseCase.execute(vacancy)
        refreshFavorites()
    }
}