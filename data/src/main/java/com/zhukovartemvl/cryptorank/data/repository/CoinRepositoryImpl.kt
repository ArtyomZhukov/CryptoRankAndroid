package com.zhukovartemvl.cryptorank.data.repository

import com.zhukovartemvl.cryptorank.core.exception.RepositoryFetchingException
import com.zhukovartemvl.cryptorank.core.model.CoinDTO
import com.zhukovartemvl.cryptorank.core.repository.CoinRepository
import com.zhukovartemvl.cryptorank.core.utils.*
import com.zhukovartemvl.cryptorank.data.cache.CoinsCache
import com.zhukovartemvl.cryptorank.data.converter.transform
import com.zhukovartemvl.cryptorank.data.network.api.CoinApi


class CoinRepositoryImpl(
    private val coinsCache: CoinsCache,
    private val coinApi: CoinApi
) : CoinRepository {

    override suspend fun fetchCoins(force: Boolean): Either<Exception, List<CoinDTO>> {
        if (force || !coinsCache.isCached() || coinsCache.isExpired) {
            coinApi.fetchCoins().doOnSuccess { coins ->
                coinsCache.replaceAll(coins)
                val coinDTOList = coins.map { it.transform() }
                return Success(coinDTOList)
            }.doOnFailure { exception ->
                return Failure(exception)
            }
        }
        coinsCache.getAll().doOnSuccess { coins ->
            val coinDTOList = coins.map { it.transform() }
            return Success(coinDTOList)
        }.doOnFailure { exception ->
            return Failure(exception)
        }
        return Failure(RepositoryFetchingException())
    }

}
