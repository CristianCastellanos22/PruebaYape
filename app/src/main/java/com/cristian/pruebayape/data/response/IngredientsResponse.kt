package com.cristian.pruebayape.data.response

import com.google.gson.annotations.SerializedName

data class IngredientsResponse(
    @SerializedName("strIngredient")
    val strIngredient: String?,
)