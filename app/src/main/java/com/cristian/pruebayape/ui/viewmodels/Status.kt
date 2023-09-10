package com.cristian.pruebayape.ui.viewmodels

sealed class Status {
    data class Success<T>(val data: T): Status()
    object Loading: Status()
    data class Error(val message: String): Status()
    data class UpdateData<T>(val data: T): Status()
}