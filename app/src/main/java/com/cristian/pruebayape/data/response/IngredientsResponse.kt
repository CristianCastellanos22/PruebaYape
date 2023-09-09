package com.cristian.pruebayape.data.response

import com.cristian.pruebayape.domain.models.IngredientsUI
import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    @SerializedName("strIngredient")
    val ingredient: String?,
)

fun IngredientsResponse.toDomain(): IngredientsUI {
    return IngredientsUI(
        ingredient = ingredient,
    )
}
