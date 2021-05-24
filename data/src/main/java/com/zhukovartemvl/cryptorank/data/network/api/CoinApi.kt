package com.zhukovartemvl.cryptorank.data.network.api

import com.zhukovartemvl.cryptorank.core.utils.Either
import com.zhukovartemvl.cryptorank.data.entity.CoinEntity


interface CoinApi {
    suspend fun fetchCoins(): Either<Exception, List<CoinEntity>>
}
