package com.nerazim.emtest.data

import android.content.Context
import com.google.gson.Gson
import com.nerazim.emtest.domain.models.Data
import com.nerazim.emtest.domain.models.Offer
import com.nerazim.emtest.domain.models.Vacancy
import com.nerazim.emtest.domain.repository.DataRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

//реализация репозитория
class DataRepositoryImpl(private val apiHelper: ApiHelper, context: Context): DataRepository {
    //данные
    private var data = Data()

    //конструктор
    init {
        //получение данных при создании
        CoroutineScope(Dispatchers.IO).launch {
            loadData(context)
        }
    }

    //загрузка данных
    override suspend fun loadData(context: Context) {
        data = coroutineScope {
            //пытаемся загрузить данные из API
            try {
                apiHelper.getData()
            }
            //если не получается, берем данные из локального файла
            catch (_: Exception) {
                val json: String = context.assets.open("мок json.json").bufferedReader().use { it.readText() }
                Gson().fromJson(json, Data::class.java)
            }
        }
    }

    //получение рекомендаций
    override fun getOffers(): List<Offer> {
        return data.offers
    }

    //получение вакансий
    override fun getVacancies(): List<Vacancy> {
        return data.vacancies
    }

    //получение вакансий в избранном
    override fun getFavorites(): List<Vacancy> {
        return data.vacancies.filter { it.isFavorite == true }
    }

    //получение количества вакансий в избранном
    override fun getFavoritesNumber(): Int {
        data.favoriteNumber = getFavorites().size
        return data.favoriteNumber
    }

    //добавить вакансию в избранное
    override fun addFavorite(vacancy: Vacancy) {
        data.vacancies.find { it == vacancy }?.isFavorite = true
        data.favoriteNumber += 1
    }

    //удалить вакансию из избранного
    override fun removeFavorite(vacancy: Vacancy) {
        data.vacancies.find { it == vacancy }?.isFavorite = false
        data.favoriteNumber -= 1
    }

}