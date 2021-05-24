package com.zhukovartemvl.cryptorank.data.network.api.impl

import com.zhukovartemvl.cryptorank.core.utils.Either
import com.zhukovartemvl.cryptorank.data.entity.CoinEntity
import com.zhukovartemvl.cryptorank.data.network.api.CoinApi
import com.zhukovartemvl.cryptorank.data.network.extension.intercept
import com.zhukovartemvl.cryptorank.data.network.response.CoinResponse
import io.ktor.client.*
import io.ktor.client.request.*


class CoinApiImpl(
    private val client: HttpClient,
    private val baseUrl: String = "https://api.coinranking.com/v2"
) : CoinApi {
    override suspend fun fetchCoins(): Either<Exception, List<CoinEntity>> {
        return client.intercept {
            client.get<CoinResponse>(urlString = "$baseUrl/coins").data.coins
        }
    }
}
