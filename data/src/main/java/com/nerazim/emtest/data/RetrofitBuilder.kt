package com.nerazim.emtest.data

import com.nerazim.emtest.domain.models.DataApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    private val BASE_URL = "https://drive.usercontent.google.com/u/0/"

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val dataApi: DataApi = getRetrofit().create(DataApi::class.java)
}