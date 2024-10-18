package com.nerazim.emtest.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nerazim.emtest.domain.usecases.AddToFavoritesUseCase
import com.nerazim.emtest.domain.usecases.GetOffersUseCase
import com.nerazim.emtest.domain.usecases.GetVacanciesUseCase
import com.nerazim.emtest.domain.usecases.RemoveFromFavoritesUseCase
import com.nerazim.emtest.domain.models.Offer
import com.nerazim.emtest.domain.models.Vacancy

class SearchViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
): ViewModel() {

    private val offersLiveMutable = MutableLiveData<List<Offer>>()
    private val offersLive: LiveData<List<Offer>>
        get() = offersLiveMutable

    private fun refreshOffers() {
        val newList = getOffersUseCase.execute()
        offersLiveMutable.value = newList.map { it.copy() }
    }

    private fun refreshVacancies() {
        val newList = getVacanciesUseCase.execute()
        vacanciesLiveMutable.value = newList.map { it.copy() }
    }

    fun getOfferListLive(): LiveData<List<Offer>> {
        refreshOffers()
        return offersLive
    }

    private val vacanciesLiveMutable = MutableLiveData<List<Vacancy>>()
    private val vacanciesLive: LiveData<List<Vacancy>>
        get() = vacanciesLiveMutable

    fun getVacancyListLive(): LiveData<List<Vacancy>> {
        refreshVacancies()
        return vacanciesLive
    }

    fun addToFavorites(vacancy: Vacancy) {
        addToFavoritesUseCase.execute(vacancy)
        refreshVacancies()
        //bbvm.refreshFavoriteNumber()
    }

    fun removeFromFavorites(vacancy: Vacancy) {
        removeFromFavoritesUseCase.execute(vacancy)
        refreshVacancies()
        //bbvm.refreshFavoriteNumber()
    }
}