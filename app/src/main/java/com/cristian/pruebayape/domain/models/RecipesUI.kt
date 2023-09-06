package com.cristian.pruebayape.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipesUI(
    val id: String,
    val idMeal: String,
    val nameMeal: String,
    val nameCategory: String,
    val nameArea: String,
    val strInstructions: String,
    val strMealThumb: String,
    val strTags: String,
    val data: ArrayList<IngredientsUI>,
    val lat: Double,
    val lng: Double
) : Parcelable