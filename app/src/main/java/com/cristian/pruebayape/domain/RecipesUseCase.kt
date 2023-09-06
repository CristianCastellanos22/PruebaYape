package com.cristian.pruebayape.domain

import com.cristian.pruebayape.data.response.ApiResponseStatus
import com.cristian.pruebayape.domain.models.RecipesUI

interface RecipesUseCase {
    suspend fun invoke(): ApiResponseStatus<List<RecipesUI>>
}