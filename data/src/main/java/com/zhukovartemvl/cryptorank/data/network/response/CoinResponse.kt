package com.zhukovartemvl.cryptorank.data.network.response

import com.zhukovartemvl.cryptorank.data.entity.CoinEntity
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class CoinResponse(
    @SerialName("data") val data: CoinDataResponse
)

@Serializable
data class CoinDataResponse(
    @SerialName("coins") val coins: List<CoinEntity>
)
