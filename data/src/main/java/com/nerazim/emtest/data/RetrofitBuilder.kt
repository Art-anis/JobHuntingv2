package com.nerazim.emtest.data

import com.nerazim.emtest.domain.DataApi
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

    val dataApi: com.nerazim.emtest.domain.DataApi = getRetrofit().create(com.nerazim.emtest.domain.DataApi::class.java)
}