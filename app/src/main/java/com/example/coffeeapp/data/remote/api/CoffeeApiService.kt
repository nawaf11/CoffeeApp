package com.example.coffeeapp.data.remote.api

import com.example.coffeeapp.data.remote.models.CoffeeDto
import retrofit2.Response
import retrofit2.http.GET

interface CoffeeApiService {

    @GET("/coffee/hot")
    suspend fun getCoffeeList() : Response<List<CoffeeDto>>

}