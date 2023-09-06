package com.cristian.pruebayape.data.network

import com.cristian.pruebayape.data.response.RecipesResponse
import retrofit2.Response
import retrofit2.http.GET

interface RecipesClient {
    @GET("/meals")
    suspend fun doGetRecipes(): Response<List<RecipesResponse>>
}