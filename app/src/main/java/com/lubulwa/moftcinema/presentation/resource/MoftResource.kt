package com.lubulwa.cleanarchsample.presentation.data

open class MoftResource<out T> constructor(val status: ResourceState, val data: T?, val message: String?) {

    fun <T> success(data: T): MoftResource<T> {
        return MoftResource(ResourceState.SUCCESS, data, null)
    }

    fun <T> error(message: String, data: T?): MoftResource<T> {
        return MoftResource(ResourceState.ERROR, null, message)
    }

    fun <T> loading(): MoftResource<T> {
        return MoftResource(ResourceState.LOADING, null, null)
    }

}