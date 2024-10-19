package com.nerazim.emtest.domain.models

import retrofit2.http.GET

//интерфейс для запроса данных из сети
interface DataApi {
    //получение данных из endpoint
    @GET("uc/?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getData(): Data
}