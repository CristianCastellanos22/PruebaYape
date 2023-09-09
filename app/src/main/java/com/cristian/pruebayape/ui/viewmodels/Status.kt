package com.cristian.pruebayape.ui.viewmodels

sealed class Status<T> {
    class Success<T>(val data: T): Status<T>()
    class Loading<T>: Status<T>()
    class Error<T>(val messageId: String): Status<T>()
    class UpdateData<T>(val data: T): Status<T>()
}