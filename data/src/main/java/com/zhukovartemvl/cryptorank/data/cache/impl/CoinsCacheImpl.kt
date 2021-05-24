package com.zhukovartemvl.cryptorank.data.cache.impl

import com.zhukovartemvl.cryptorank.data.cache.CoinsCache
import com.zhukovartemvl.cryptorank.data.cache.impl.base.BaseCacheImpl
import com.zhukovartemvl.cryptorank.data.database.AppDatabase
import com.zhukovartemvl.cryptorank.data.database.dao.base.CacheBaseDao
import com.zhukovartemvl.cryptorank.data.entity.CoinEntity
import com.zhukovartemvl.cryptorank.data.preferences.CachePreferences


class CoinsCacheImpl(preferences: CachePreferences, database: AppDatabase) :
    BaseCacheImpl<CoinEntity>(preferences), CoinsCache {

    override val expirationTime: Long = 600000 //10 minutes
    override val lastUpdateKey: String = "CoinLastUpdateKey"
    override val cacheDao: CacheBaseDao<CoinEntity> = database.coinCacheDao()

}
