package com.example.coffeeapp.data.remote

import retrofit2.Response
import java.net.UnknownHostException

/**
 * Success: httpCode is success (ex: 200) and response successfully parsed.
 * Success: httpCode is error (ex: 500, 404 ..)
 * Exception: for example couldn't reach the server (no network, server id down ..)
 */
enum class NetworkResultStatus {
    Success,
    Error
}

/**
 * HttpError: httpCode is error (ex: 500, 404 ..)
 * ConnectionError: ex: no internet connection, server down ..
 * UnknownError: other error ....
 */
enum class NetworkErrorType {
    HttpError, //
    ConnectionError, // for example: np
    UnknownError
}

data class NetworkResult<out T> constructor(
    val status : NetworkResultStatus,
    val data: T?,
    val errorType: NetworkErrorType?,
    val httpStatusCode: Int?,
    val exception : Throwable?) {

    companion object {

        fun <T> success(data : T?, httpStatusCode :Int?) : NetworkResult<T> {
            return NetworkResult(
                status = NetworkResultStatus.Success,
                data = data,
                httpStatusCode = httpStatusCode,
                errorType = null,
                exception = null
            )
        }

        fun <T> error(httpStatusCode :Int?,
                      errorType: NetworkErrorType,
                      exception : Throwable?) : NetworkResult<T> {
            return NetworkResult(
                status = NetworkResultStatus.Error,
                data = null,
                httpStatusCode = httpStatusCode,
                errorType = errorType,
                exception = exception
            )
        }
    }

    fun isSuccess() = status == NetworkResultStatus.Success

}

object NetworkCaller {

    suspend fun <T : Any> call(
        execute: suspend () -> Response<T>
    ): NetworkResult<T> {

        try {
            val response = execute()

            if (response.isSuccessful)
                return NetworkResult.success(execute().body()!!,
                    response.code())
            else {
                return NetworkResult.error(
                    httpStatusCode = response.code(),
                    errorType = NetworkErrorType.HttpError,
                    exception = null
                    )
            }
        } catch (e : Exception) {
            return handleExceptionErrorResult(e)
        }
    }

    private fun <T> handleExceptionErrorResult(e : Exception) : NetworkResult<T> {

        return when(e) {
            is UnknownHostException -> {
                return NetworkResult.error(
                    httpStatusCode = null,
                    errorType = NetworkErrorType.ConnectionError,
                    exception = e
                )
            }
            else -> {
                return NetworkResult.error(
                    httpStatusCode = null,
                    errorType = NetworkErrorType.UnknownError,
                    exception = e
                )
            }
        }

    }

}