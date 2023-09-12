package com.cristian.pruebayape.utils

import android.content.Context
import androidx.annotation.RawRes

/**
 * Reads input file and converts to json
 */
fun Context.rawResToString(@RawRes rawFile: Int): String {
    return this.resources.openRawResource(rawFile).bufferedReader().use { it.readText() }
}