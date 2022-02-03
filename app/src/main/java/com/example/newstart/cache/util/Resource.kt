package com.example.newstart.cache.util

/**
 * Created by Ashutosh Ojha on 02,February,2022
 */
sealed class Resource<T>(val data: T? = null, val error: Throwable? = null) {

    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(throwable: Throwable, data: T? = null) : Resource<T>(data, throwable)
}