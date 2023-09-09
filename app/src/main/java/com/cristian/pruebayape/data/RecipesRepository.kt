package com.cristian.pruebayape.data

import com.cristian.pruebayape.domain.models.RecipesUI

interface RecipesRepository {
    suspend fun getRecipes(): Result<List<RecipesUI>>
}