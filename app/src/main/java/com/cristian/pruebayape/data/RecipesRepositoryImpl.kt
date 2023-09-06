package com.cristian.pruebayape.data

import com.cristian.pruebayape.data.response.ApiResponseStatus
import com.cristian.pruebayape.data.mappers.toDomain
import com.cristian.pruebayape.data.network.RecipesClient
import com.cristian.pruebayape.data.network.bodyOrException
import com.cristian.pruebayape.domain.models.RecipesUI
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RecipesRepositoryImpl @Inject constructor(
    private val recipesServices: RecipesClient,
    private val dispatcher: CoroutineDispatcher,
) : RecipesRepository {
    override suspend fun getRecipes(): ApiResponseStatus<List<RecipesUI>> {
        try {
            val response = recipesServices.doGetRecipes().bodyOrException()
            val recipes = response.map { it.toDomain() }
            return ApiResponseStatus.Success(recipes)
        } catch (e: Exception) {
            return ApiResponseStatus.Error("Error en consulta \nSugerencias: \n - Verifique su conexión a internet. \n - Intente más tarde.")
        }
    }


}