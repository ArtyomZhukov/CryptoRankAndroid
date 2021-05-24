package com.zhukovartemvl.cryptorank.core.model


data class CoinDTO(
    val uuid: String,
    val name: String,
    val symbol: String,
    val iconUrl: String,
    val price: Float,
    val marketCap: Float,
    val change: Float,
    val rank: Int,
    val sparkline: List<Float>
)
