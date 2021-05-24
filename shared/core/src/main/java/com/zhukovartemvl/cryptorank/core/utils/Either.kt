package com.zhukovartemvl.cryptorank.core.utils

/**
 * An algebraic data type to provide either a [common.Failure][F] or a [common.Success][S] result.
 */
sealed class Either<out F, out S> {

    /**
     * Calls [failed] with the [failure][Failure.failure] value if result is a [Failure]
     * or [succeeded] with the [success][Success.success] value if result is a [Success]
     */
    inline fun <T> fold(failed: (F) -> T, succeeded: (S) -> T): T =
        when (this) {
            is Failure -> failed(failure)
            is Success -> succeeded(success)
        }
}

data class Failure<out F>(val failure: F) : Either<F, Nothing>()
data class Success<out S>(val success: S) : Either<Nothing, S>()

inline fun <F, S> Either<F, S>.get(): S {
    return throttle(null)!!
}

inline fun <F, S> Either<F, S>.throttle(def: S): S {
    return when(this) {
        is Success -> success
        is Failure -> def
    }
}

inline fun <F, S1> Either<F, S1>.doOnFailure(toDo: (F) -> Unit): Either<F, S1> = fold({
    toDo(it)
    this as Failure
}, { this as Success })


inline fun <F, S1> Either<F, S1>.doOnSuccess(toDo: (S1) -> Unit): Either<F, S1> = fold({ this as Failure }, {
    toDo(it)
    this as Success
})
/**
 * Allows chaining of multiple calls taking as argument the [success][Success.success] value of the previous call and
 * returning an [Either].
 *
 * 1. Unwrap the result of the first call from the [Either] wrapper.
 * 2. Check if it is a [Success].
 * 3. If yes, call the next function (passed as [ifSucceeded]) with the value of the [success][Success.success]
 * property as an input parameter (chain the calls).
 * 4. If no, just pass the [Failure] through as the end result of the whole call chain.
 *
 * In case any of the calls in the chain returns a [Failure], none of the subsequent flatmapped functions is called
 * and the whole chain returns this failure.
 *
 * @param ifSucceeded next function which should be called if this is a [Success]. The [success][Success.success]
 * value will be then passed as the input parameter.
 */
inline fun <F, S1, S2> Either<F, S1>.flatMap(succeeded: (S1) -> Either<F, S2>): Either<F, S2> =
    fold({ this as Failure }, succeeded)

/**
 * Map the [Success] value of the [Either] to another value.
 *
 * You can for example common.map an `common.Success<String>` to an `common.Success<Int>` by
 * using the following code:
 * ```
 * val fiveString: common.Either<Nothing, String> = common.Success("5")
 * val fiveInt : common.Either<Nothing, Int> = fiveString.common.map { it.toInt() }
 * ```
 */
inline fun <F, S1, S2> Either<F, S1>.map(f: (S1) -> S2): Either<F, S2> =
    flatMap { Success(f(it)) }