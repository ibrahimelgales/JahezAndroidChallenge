package net.jahez.jahezchallenge.core.utils

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

class AppException(
    private val throwable: Throwable? = null,
    private val statusCode: Int? = null,
    private val errors: HashMap<String?, ArrayList<String>?>? = null,
    private val extraData: HashMap<String?, String?>? = null,
    private var message: String? = null, private val apiCode: String? = null
) {

    private val type: ExceptionType


    fun getExceptionType() = type

    fun getMessage() = getMessageFromErrors()
    fun getExtraData() = extraData
    fun getOnlyMessage() = message ?: ""
    fun getApiCode() = apiCode ?: ""

    fun getMessageFromErrors(): String? {
        if (errors?.isEmpty() == true)
            return message
        return errors?.values?.first()?.first() ?: message
    }

    init {
        type = getTypeFromThrowable()
        if (message == null) {
            message = when (type) {
                ExceptionType.SERVER_DOWN -> "SERVER_DOWN"
                ExceptionType.TIME_OUT -> "TIME_OUT"
                ExceptionType.UNAUTHORIZED -> "UNAUTHORIZED"
                ExceptionType.UNKNOWN -> "UNKNOWN"
                ExceptionType.HTTP -> "HTTP"
                ExceptionType.NETWORK -> "No internet connection"
            }
        }

    }


    private fun getTypeFromThrowable(): ExceptionType {
        return when (throwable) {
            is HttpException, null -> {
                when (statusCode) {
                    500, 502 -> ExceptionType.SERVER_DOWN
                    408 -> ExceptionType.TIME_OUT
                    401 -> ExceptionType.UNAUTHORIZED
                    else -> ExceptionType.UNKNOWN
                }
            }
            is TimeoutException, is SocketTimeoutException -> ExceptionType.TIME_OUT
            is IOException, is UnknownHostException, is ConnectException -> ExceptionType.NETWORK
            else -> ExceptionType.UNKNOWN
        }
    }

    public enum class ExceptionType {
        HTTP, //Status is not 200,
        NETWORK, //Internet Error
        UNKNOWN,
        SERVER_DOWN, //Server didn't respond properly
        TIME_OUT,
        UNAUTHORIZED
    }
}