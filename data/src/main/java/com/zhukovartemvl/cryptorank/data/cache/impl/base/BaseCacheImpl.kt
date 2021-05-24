package com.zhukovartemvl.cryptorank.data.cache.impl.base

import com.zhukovartemvl.cryptorank.core.exception.CacheFetchingException
import com.zhukovartemvl.cryptorank.core.utils.Either
import com.zhukovartemvl.cryptorank.core.utils.Failure
import com.zhukovartemvl.cryptorank.core.utils.Success
import com.zhukovartemvl.cryptorank.data.cache.base.Cache
import com.zhukovartemvl.cryptorank.data.database.dao.base.CacheBaseDao
import com.zhukovartemvl.cryptorank.data.preferences.CachePreferences


abstract class BaseCacheImpl<T>(private val preferences: CachePreferences) : Cache<T> {

    abstract val expirationTime: Long

    abstract val lastUpdateKey: String

    abstract val cacheDao: CacheBaseDao<T>

    override val isExpired: Boolean
        get() {
            val currentTime = System.currentTimeMillis()
            val lastUpdateTime = getLastCacheUpdateTimeMillis()
            val expired = currentTime - lastUpdateTime > expirationTime
            if (expired) {
                evictAll()
            }
            return expired
        }

    override fun get(id: Int): Either<Exception, T> {
        return try {
            val entity = cacheDao.getById(id)
            return if (entity != null) {
                Success(entity)
            } else {
                Failure(CacheFetchingException())
            }
        } catch (e: Exception) {
            Failure(CacheFetchingException(e.toString()))
        }
    }

    override fun getAll(): Either<Exception, List<T>> {
        return try {
            val entity = cacheDao.getAll()
            return Success(entity)
        } catch (e: Exception) {
            Failure(CacheFetchingException(e.toString()))
        }
    }

    override fun putAll(entities: List<T>) {
        cacheDao.insertAll(entities)
        setLastCacheUpdateTimeMillis()
    }

    override fun replaceAll(entities: List<T>) {
        evictAll()
        putAll(entities)
    }

    override fun isCached(): Boolean {
        return cacheDao.itemsCount() != 0
    }

    override fun evictAll() {
        cacheDao.deleteAll()
    }

    private fun setLastCacheUpdateTimeMillis() {
        val currentMillis = System.currentTimeMillis()
        preferences.setLongValueByKey(lastUpdateKey, currentMillis)
    }

    private fun getLastCacheUpdateTimeMillis(): Long {
        return preferences.getLongValueByKey(lastUpdateKey)
    }

}
