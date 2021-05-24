package com.zhukovartemvl.cryptorank.core.repository

import com.zhukovartemvl.cryptorank.core.model.CoinDTO
import com.zhukovartemvl.cryptorank.core.utils.Either


interface CoinRepository {
    suspend fun fetchCoins(force: Boolean = false): Either<Exception, List<CoinDTO>>
}
