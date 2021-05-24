package com.zhukovartemvl.cryptorank.data.database.dao.base

import androidx.room.*
import androidx.sqlite.db.SupportSQLiteQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.room.RawQuery
import androidx.room.Delete
import androidx.room.OnConflictStrategy
import java.lang.reflect.ParameterizedType


@Dao
abstract class CacheBaseDao<T>() {

    open fun getAll(): List<T> {
        val query = SimpleSQLiteQuery(
            "select * from " + getTableName()
        )
        return doGetAll(query)
    }

    open fun getById(id: Int): T {
        val query = SimpleSQLiteQuery(
            "select * from " + getTableName() + " where id = ?", arrayOf<Any>(id)
        )
        return doGetById(query)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<T>): LongArray

    @Delete
    abstract fun delete(entity: T)

    open fun deleteAll(): Int {
        val query = SimpleSQLiteQuery("delete from " + getTableName())
        return doDeleteAll(query)
    }

    open fun getTableName(): String {
        val clazz =
            (javaClass.superclass.genericSuperclass as ParameterizedType).actualTypeArguments[0] as Class<*>
        return clazz.simpleName
    }

    fun itemsCount(): Int {
        val query = SimpleSQLiteQuery("select count(*) from " + getTableName())
        return doGetItemsCount(query)
    }

    @RawQuery
    protected abstract fun doGetAll(query: SupportSQLiteQuery): List<T>

    @RawQuery
    protected abstract fun doGetById(query: SupportSQLiteQuery): T

    @RawQuery
    protected abstract fun doDeleteAll(query: SupportSQLiteQuery): Int

    @RawQuery
    protected abstract fun doGetItemsCount(query: SupportSQLiteQuery): Int
}
