package com.nerazim.emtest.domain

import retrofit2.http.GET

interface DataApi {
    @GET("uc/?id=1z4TbeDkbfXkvgpoJprXbN85uCcD7f00r&export=download")
    suspend fun getData(): Data
}