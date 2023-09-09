package com.cristian.pruebayape.data.response

import com.cristian.pruebayape.domain.models.OriginUI
import com.cristian.pruebayape.domain.models.RecipesUI
import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("idMeal")
    val id: String,
    @SerializedName("nameMeal")
    val name: String,
    @SerializedName("nameCategory")
    val category: String,
    @SerializedName("nameArea")
    val area: String,
    @SerializedName("strInstructions")
    val instructions: String,
    @SerializedName("strMealThumb")
    val thumbnail: String,
    @SerializedName("strTags")
    val tags: String,
    @SerializedName("data")
    val ingredients: List<IngredientsResponse>,
    val lat: Double,
    val lng: Double
)

fun RecipesResponse.toDomain(): RecipesUI {
    return RecipesUI(
        id = id,
        name = name,
        category = category,
        area = area,
        instructions = instructions,
        thumbnail = thumbnail,
        tags = tags,
        ingredients = ingredients.map { it.toDomain() },
        originUI = OriginUI(
            lat = lat,
            lng = lng
        )
    )
}
