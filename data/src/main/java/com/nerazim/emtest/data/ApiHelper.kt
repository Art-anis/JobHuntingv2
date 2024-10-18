package com.nerazim.emtest.data

import com.nerazim.emtest.domain.models.DataApi

class ApiHelper(private val dataApi: DataApi) {
    suspend fun getData() = dataApi.getData()
}