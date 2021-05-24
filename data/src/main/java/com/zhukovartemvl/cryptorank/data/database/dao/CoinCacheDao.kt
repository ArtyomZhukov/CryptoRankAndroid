package com.zhukovartemvl.cryptorank.data.database.dao

import androidx.room.Dao
import com.zhukovartemvl.cryptorank.data.database.dao.base.CacheBaseDao
import com.zhukovartemvl.cryptorank.data.entity.CoinEntity


@Dao
abstract class CoinCacheDao : CacheBaseDao<CoinEntity>()
