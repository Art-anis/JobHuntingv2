package com.nerazim.emtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nerazim.emtest.domain.AddToFavoritesUseCase
import com.nerazim.emtest.domain.GetOffersUseCase
import com.nerazim.emtest.domain.GetVacanciesUseCase
import com.nerazim.emtest.domain.RemoveFromFavoritesUseCase

class SearchViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
): ViewModel() {

    private val offersLiveMutable = MutableLiveData<List<com.nerazim.emtest.domain.Offer>>()
    private val offersLive: LiveData<List<com.nerazim.emtest.domain.Offer>>
        get() = offersLiveMutable

    private fun refreshOffers() {
        val newList = getOffersUseCase.execute()
        offersLiveMutable.value = newList.map { it.copy() }
    }

    private fun refreshVacancies() {
        val newList = getVacanciesUseCase.execute()
        vacanciesLiveMutable.value = newList.map { it.copy() }
    }

    fun getOfferListLive(): LiveData<List<com.nerazim.emtest.domain.Offer>> {
        refreshOffers()
        return offersLive
    }

    private val vacanciesLiveMutable = MutableLiveData<List<com.nerazim.emtest.domain.Vacancy>>()
    private val vacanciesLive: LiveData<List<com.nerazim.emtest.domain.Vacancy>>
        get() = vacanciesLiveMutable

    fun getVacancyListLive(): LiveData<List<com.nerazim.emtest.domain.Vacancy>> {
        refreshVacancies()
        return vacanciesLive
    }

    fun addToFavorites(vacancy: com.nerazim.emtest.domain.Vacancy) {
        addToFavoritesUseCase.execute(vacancy)
        refreshVacancies()
        //bbvm.refreshFavoriteNumber()
    }

    fun removeFromFavorites(vacancy: com.nerazim.emtest.domain.Vacancy) {
        removeFromFavoritesUseCase.execute(vacancy)
        refreshVacancies()
        //bbvm.refreshFavoriteNumber()
    }
}