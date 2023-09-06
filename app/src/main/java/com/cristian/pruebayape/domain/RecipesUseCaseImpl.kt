package com.cristian.pruebayape.domain

import com.cristian.pruebayape.data.RecipesRepository
import javax.inject.Inject

class RecipesUseCaseImpl @Inject constructor(private val repository: RecipesRepository) :
    RecipesUseCase {
    override suspend fun invoke() = repository.getRecipes()
}