package com.example.dogschallenge.data.datasource.remote.exception

sealed class DataException(message: String) : Exception(message) {
    data class DogsException(override val message: String = "Some error happened with the get Dogs Data") :
        Exception(message)
}
