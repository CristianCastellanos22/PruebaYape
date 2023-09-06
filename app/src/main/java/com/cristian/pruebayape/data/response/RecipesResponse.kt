package com.cristian.pruebayape.data.response

import com.google.gson.annotations.SerializedName

data class RecipesResponse(
    @SerializedName("id")
    val id: String,
    @SerializedName("idMeal")
    val idMeal: String,
    @SerializedName("nameMeal")
    val nameMeal: String,
    @SerializedName("nameCategory")
    val nameCategory: String,
    @SerializedName("nameArea")
    val nameArea: String,
    @SerializedName("strInstructions")
    val strInstructions: String,
    @SerializedName("strMealThumb")
    val strMealThumb: String,
    @SerializedName("strTags")
    val strTags: String,
    @SerializedName("data")
    val data: ArrayList<IngredientsResponse>,
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double
)