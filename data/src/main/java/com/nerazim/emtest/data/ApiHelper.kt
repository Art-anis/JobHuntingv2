package com.nerazim.emtest.data

import com.nerazim.emtest.domain.models.DataApi

//класс для доступа к API
class ApiHelper(private val dataApi: DataApi) {
    suspend fun getData() = dataApi.getData()
}