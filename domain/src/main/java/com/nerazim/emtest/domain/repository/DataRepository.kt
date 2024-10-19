package com.nerazim.emtest.domain.repository

import android.content.Context
import com.nerazim.emtest.domain.models.Offer
import com.nerazim.emtest.domain.models.Vacancy

//репозиторий, в котором будут храниться данные
interface DataRepository {

    //загрузка данных
    suspend fun loadData(context: Context)

    //получение рекомендаций
    fun getOffers(): List<Offer>

    //получение вакансий
    fun getVacancies(): List<Vacancy>

    //получение вакансий в избранном
    fun getFavorites(): List<Vacancy>

    //получение количества вакансий в избранном
    fun getFavoritesNumber(): Int

    //добавить вакансию в избранное
    fun addFavorite(vacancy: Vacancy)

    //удалить вакансию из избранного
    fun removeFavorite(vacancy: Vacancy)
}