package com.nerazim.emtest.domain

import android.content.Context

//репозиторий, в котором будут храниться данные
interface DataRepository {

    //загрузка данных в виде JSON-файла и его десериализация
    suspend fun loadData(context: Context)

    //выделение списка рекомендаций
    fun getOffers(): List<Offer>

    //выделение списка вакансий
    fun getVacancies(): List<Vacancy>

    fun getFavorites(): List<Vacancy>

    fun getFavoritesNumber(): Int

    fun addFavorite(vacancy: Vacancy)

    fun removeFavorite(vacancy: Vacancy)
}