package com.cristian.pruebayape.data.mappers

import com.cristian.pruebayape.data.response.IngredientsResponse
import com.cristian.pruebayape.data.response.RecipesResponse
import com.cristian.pruebayape.domain.models.IngredientsUI
import com.cristian.pruebayape.domain.models.RecipesUI

fun RecipesResponse.toDomain(): RecipesUI {
    return RecipesUI(
        id = id,
        idMeal = idMeal,
        nameMeal = nameMeal,
        nameCategory = nameCategory,
        nameArea = nameArea,
        strInstructions = strInstructions,
        strMealThumb = strMealThumb,
        strTags = strTags,
        data = ArrayList(data.map { it.toDomain() }),
        lat = lat,
        lng = lng
    )
}

fun IngredientsResponse.toDomain(): IngredientsUI {
    return IngredientsUI(
        strIngredient = strIngredient,
    )
}