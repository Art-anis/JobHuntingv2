package com.nerazim.emtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nerazim.emtest.domain.usecases.GetFavoritesNumberUseCase

//viewmodel для количества вакансий в избранном
class FavoriteNumberViewModel(
    private val getFavoritesNumberUseCase: GetFavoritesNumberUseCase
): ViewModel() {

    //livedata
    private val favoritesNumberLiveMutable = MutableLiveData<Int>()
    private val favoritesNumberLive: LiveData<Int>
        get() = favoritesNumberLiveMutable

    //получение livedata
    fun getFavoritesNumber(): LiveData<Int> {
        refreshFavoritesNumber()
        return favoritesNumberLive
    }

    //обновление livedata
    fun refreshFavoritesNumber() {
        favoritesNumberLiveMutable.value = getFavoritesNumberUseCase.execute()
    }
}