package com.example.dogschallenge.core.extensions

const val DEFAULT_VALUE = 0

fun Int?.orDefault() = this ?: DEFAULT_VALUE
