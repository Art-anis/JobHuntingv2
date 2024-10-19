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

//viewmodel для экрана поиска
class SearchViewModel(
    private val getOffersUseCase: GetOffersUseCase,
    private val getVacanciesUseCase: GetVacanciesUseCase,
    private val addToFavoritesUseCase: AddToFavoritesUseCase,
    private val removeFromFavoritesUseCase: RemoveFromFavoritesUseCase
): ViewModel() {

    //livedata рекомендаций
    private val offersLiveMutable = MutableLiveData<List<Offer>>()
    private val offersLive: LiveData<List<Offer>>
        get() = offersLiveMutable

    //livedata вакансий
    private val vacanciesLiveMutable = MutableLiveData<List<Vacancy>>()
    private val vacanciesLive: LiveData<List<Vacancy>>
        get() = vacanciesLiveMutable

    //обновить рекомендации
    private fun refreshOffers() {
        val newList = getOffersUseCase.execute()
        offersLiveMutable.value = newList.map { it.copy() }
    }

    //обновить вакансии
    private fun refreshVacancies() {
        val newList = getVacanciesUseCase.execute()
        vacanciesLiveMutable.value = newList.map { it.copy() }
    }

    //получение livedata рекомендаций
    fun getOfferListLive(): LiveData<List<Offer>> {
        refreshOffers()
        return offersLive
    }

    //получение livedata вакансий
    fun getVacancyListLive(): LiveData<List<Vacancy>> {
        refreshVacancies()
        return vacanciesLive
    }

    //добавление вакансии в избранное
    fun addToFavorites(vacancy: Vacancy) {
        addToFavoritesUseCase.execute(vacancy)
        refreshVacancies()
    }

    //удаление вакансии из избранного
    fun removeFromFavorites(vacancy: Vacancy) {
        removeFromFavoritesUseCase.execute(vacancy)
        refreshVacancies()
    }
}