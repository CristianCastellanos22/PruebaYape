package com.cristian.pruebayape.domain.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OriginUI(
    val lat: Double,
    val lng: Double
) : Parcelable
