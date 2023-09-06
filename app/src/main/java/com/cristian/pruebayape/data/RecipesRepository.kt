package com.cristian.pruebayape.data

import com.cristian.pruebayape.data.response.ApiResponseStatus
import com.cristian.pruebayape.domain.models.RecipesUI

interface RecipesRepository {
    suspend fun getRecipes(): ApiResponseStatus<List<RecipesUI>>
}