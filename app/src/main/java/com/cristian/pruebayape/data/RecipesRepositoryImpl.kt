package com.cristian.pruebayape.data

import com.cristian.pruebayape.data.network.RecipesClient
import com.cristian.pruebayape.data.network.resultOf
import com.cristian.pruebayape.data.response.toDomain
import com.cristian.pruebayape.domain.models.RecipesUI
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesServices: RecipesClient,
    private val dispatcher: CoroutineDispatcher,
) : RecipesRepository {
    override suspend fun getRecipes(): Result<List<RecipesUI>> = resultOf {
        val result = withContext(dispatcher) {
            recipesServices.doGetRecipes()
        }
        val body = result.body()

        if (result.isSuccessful && body != null) {
            body.map { it.toDomain() }
        } else {
            val errorMessage = result.errorBody().toString()
            error(errorMessage)
        }
    }

}
