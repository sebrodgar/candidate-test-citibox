package com.srg.citibox.common.data.model

/**
 * Created by Sebastián Rodríguez on 12,January,2020
 */

/**
 * Sealed class that represents either a success or a failure, including an associated value in each case. A Generic for the success case, and a CitiboxError in the failure case.
 */
sealed class CitiboxResult <out Failure, out Success> {

    /**
     * A failure, storing a Failure value.
     */
    data class Failure<out Failure>(val failure: Failure) : CitiboxResult<Failure, Nothing>()

    /**
     * A success, storing a Success value.
     */
    data class Success<out Success>(val success: Success) : CitiboxResult<Nothing, Success>()

    /**
     * Boolean property to know if result was a success
     */
    val isSuccess get() = this is CitiboxResult.Success<Success>
    /**
     * Boolean property to know if result was a failure
     */
    val isFailure get() = this is CitiboxResult.Failure<Failure>

}

/**
 * Method to obtain and control the failure and the success cases.
 */
fun <Failure, Success, T> CitiboxResult<Failure, Success>.fold(
    left: (Failure) -> T,
    right: (Success) -> T
): T =
    when (this) {
        is CitiboxResult.Failure -> left(failure)
        is CitiboxResult.Success -> right(success)
    }

private fun <Failure, Success, T> CitiboxResult<Failure, Success>.flatMap(f: (Success) -> CitiboxResult<Failure, T>): CitiboxResult<Failure, T> =
    fold({ this as CitiboxResult.Failure }, f)

fun <Failure, Success, T> CitiboxResult<Failure, Success>.map(f: (Success) -> T): CitiboxResult<Failure, T> =
    flatMap { CitiboxResult.Success(f(it)) }

/**
 * Method to obtain and control the failure case.
 */
fun <Failure, Success> CitiboxResult<Failure, Success>.foldFailure(): Failure? =
    when (this) {
        is CitiboxResult.Failure -> failure
        else -> null
    }

/**
 * Method to obtain and control the success case.
 */
fun <Failure, Success> CitiboxResult<Failure, Success>.foldSuccess(): Success? =
    when (this) {
        is CitiboxResult.Success -> success
        else -> null
    }