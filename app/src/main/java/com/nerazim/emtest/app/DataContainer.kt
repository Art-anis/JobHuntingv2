package com.nerazim.emtest.app

import com.nerazim.emtest.data.ApiHelper
import com.nerazim.emtest.data.DataRepositoryImpl
import com.nerazim.emtest.data.RetrofitBuilder
import com.nerazim.emtest.domain.DataRepository

interface Container {
    val dataRepository: com.nerazim.emtest.domain.DataRepository
}

class DataContainer: Container {
    override val dataRepository: com.nerazim.emtest.domain.DataRepository by lazy {
        DataRepositoryImpl(ApiHelper(RetrofitBuilder().dataApi))
    }
}