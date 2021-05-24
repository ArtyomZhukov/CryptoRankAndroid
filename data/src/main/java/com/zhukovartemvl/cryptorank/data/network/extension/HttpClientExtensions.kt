package com.zhukovartemvl.cryptorank.data.network.extension

import com.zhukovartemvl.cryptorank.core.exception.BadResponseException
import com.zhukovartemvl.cryptorank.core.utils.Failure
import com.zhukovartemvl.cryptorank.core.utils.Success
import io.ktor.client.*
import kotlinx.serialization.SerializationException


suspend fun <Value : Any> HttpClient.intercept(block: suspend () -> Value) =
    try {
        Success(block.invoke())
    } catch (e: Exception) {
        Failure(
            when (e) {
                is SerializationException -> BadResponseException
                else -> e
            }
        )
    }
