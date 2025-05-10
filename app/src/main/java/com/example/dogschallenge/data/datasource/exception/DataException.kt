package com.example.dogschallenge.data.datasource.exception

sealed class DataException(message: String) : Exception(message) {
    data class DogsException(override val message: String = "Some error happened with the get Dogs Data") :
        Exception(message)
}
