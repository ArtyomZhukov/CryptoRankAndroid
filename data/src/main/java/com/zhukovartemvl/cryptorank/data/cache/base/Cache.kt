package com.zhukovartemvl.cryptorank.data.cache.base

import com.zhukovartemvl.cryptorank.core.utils.Either


interface Cache<T> {
    val isExpired: Boolean

    @Throws(Exception::class)
    operator fun get(id: Int): Either<Exception, T>

    @Throws(Exception::class)
    fun getAll(): Either<Exception, List<T>>

    @Throws(Exception::class)
    fun putAll(entities: List<T>)

    @Throws(Exception::class)
    fun replaceAll(entities: List<T>)

    @Throws(Exception::class)
    fun isCached(): Boolean

    @Throws(Exception::class)
    fun evictAll()
}
