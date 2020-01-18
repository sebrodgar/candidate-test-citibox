package com.srg.citibox.common.data.model

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */


/**
 * An enum containing the different errors that can occur.
 */

class CitiboxError : Exception {

    enum class CitiboxErrorType(val errorCode: Int, val httpError: Int) {

        /**
         * Internal error: Something went wrong.
         */
        INTERNAL_SERVER_ERROR(11, 500),

        /**
         * Failed.
         */
        UNKNOWN_ERROR(5, 1000);

        companion object {
            fun get(httpError: Int): CitiboxErrorType {
                return values().find { it.httpError == httpError } ?: UNKNOWN_ERROR

            }
        }


    }

    val errorType: CitiboxErrorType

    internal constructor(errorType: CitiboxErrorType, message: String? = "") : super(message) {
        this.errorType = errorType
    }

    internal constructor(errorType: CitiboxErrorType, exception: Throwable) : super(exception) {
        this.errorType = errorType
    }

    internal companion object {

        fun unknownError(): CitiboxError {
            return CitiboxError(CitiboxErrorType.UNKNOWN_ERROR)
        }

        fun internalServerError(): CitiboxError {
            return CitiboxError(CitiboxErrorType.INTERNAL_SERVER_ERROR)
        }

    }

}