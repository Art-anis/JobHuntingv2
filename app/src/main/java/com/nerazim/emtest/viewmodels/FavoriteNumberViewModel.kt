package com.nerazim.emtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nerazim.emtest.domain.usecases.GetFavoritesNumberUseCase

class FavoriteNumberViewModel(private val getFavoritesNumberUseCase: GetFavoritesNumberUseCase
): ViewModel() {

    private val favoritesNumberLiveMutable = MutableLiveData<Int>()
    private val favoritesNumberLive: LiveData<Int>
        get() = favoritesNumberLiveMutable

    fun getFavoritesNumber(): LiveData<Int> {
        refreshFavoritesNumber()
        return favoritesNumberLive
    }

    fun refreshFavoritesNumber() {
        favoritesNumberLiveMutable.value = getFavoritesNumberUseCase.execute()
    }
}