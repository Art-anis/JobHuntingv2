package com.nerazim.emtest.data

import com.nerazim.emtest.domain.DataApi

class ApiHelper(private val dataApi: com.nerazim.emtest.domain.DataApi) {
    suspend fun getData() = dataApi.getData()
}