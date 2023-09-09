package com.cristian.pruebayape.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class RecipesUI(
    val id: String,
    val name: String,
    val category: String,
    val area: String,
    val instructions: String,
    val thumbnail: String,
    val tags: String,
    val ingredients: List<IngredientsUI>,
    val originUI: OriginUI
) : Parcelable