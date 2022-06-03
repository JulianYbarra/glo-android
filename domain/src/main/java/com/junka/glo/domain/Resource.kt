package com.junka.glo.domain

sealed class Resource<out T> {
    data class Loading<T>(val loading : Boolean,val data : T) : Resource<T>()
    data class Failure<T>(val error: Error,val data : T? = null) : Resource<T>()
    data class Success<T>(val data : T) : Resource<T>()
}