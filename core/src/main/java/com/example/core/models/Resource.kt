package com.example.core.models

data class Resource<out T>(
    val status: Status,
    val data: T?,
    val message: String?,
    val errorStatus: Any?
) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(
                Status.SUCCESS,
                data,
                null,
                null
            )
        }

        fun <T> error(msg: String?, data: T?, errorStatus: Int?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                msg,
                errorStatus
            )
        }

        fun <T> exception(data: T?, exception: Any?): Resource<T> {
            return Resource(
                Status.ERROR,
                data,
                null,
                exception
            )
        }

        fun <T> loading(data: T? = null): Resource<T> {
            return Resource(
                Status.LOADING,
                data,
                null,
                null
            )
        }
    }
}
