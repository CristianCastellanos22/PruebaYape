package com.cristian.pruebayape.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class IngredientsUI(
    val ingredient: String?
) : Parcelable