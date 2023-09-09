package com.cristian.pruebayape.domain

import com.cristian.pruebayape.domain.models.RecipesUI

interface RecipesUseCase {
    suspend fun invoke(): Result<List<RecipesUI>>
}
