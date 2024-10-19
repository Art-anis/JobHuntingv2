package com.nerazim.emtest.data

import com.nerazim.emtest.domain.models.DataApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//ретрофит
class RetrofitBuilder {
    //базовый url
    private val BASE_URL = "https://drive.usercontent.google.com/u/0/"

    //получение экземпляра ретрофит
    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    //экземпляр dataApi для загрузки данных
    val dataApi: DataApi = getRetrofit().create(DataApi::class.java)
}