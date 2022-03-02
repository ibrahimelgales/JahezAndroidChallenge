package net.jahez.jahezchallenge.core

sealed class Resource<T>(val data: T? = null, val appException: AppException? = null) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(appException: AppException, data: T? = null) : Resource<T>(data, appException)
}