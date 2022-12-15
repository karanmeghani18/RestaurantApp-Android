package com.example.projectg12.api

import com.example.projectg12.models.MenuItem
import retrofit2.http.GET

interface IAPIResponse {
    @GET("all")
    suspend fun getCountries(): List<MenuItem>
}