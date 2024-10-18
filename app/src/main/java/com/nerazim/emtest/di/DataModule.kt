package com.nerazim.emtest.di

import com.nerazim.emtest.data.ApiHelper
import com.nerazim.emtest.data.DataRepositoryImpl
import com.nerazim.emtest.data.RetrofitBuilder
import com.nerazim.emtest.domain.repository.DataRepository
import org.koin.dsl.module

//модуль данных - создание репозитория
val dataModule = module {

    //репозиторий и все, что его создает - синглтоны

    //класс, имеющий доступ к API
    single<ApiHelper>(createdAtStart = true) {
        ApiHelper(dataApi = RetrofitBuilder().dataApi)
    }

    //сам репозиторий
    single<DataRepository>(createdAtStart = true) {
        DataRepositoryImpl(
            apiHelper = get(),
            context = get()
        )
    }

}