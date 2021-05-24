package com.zhukovartemvl.cryptorank.data.database.dao.base

import androidx.room.Insert
import androidx.sqlite.db.SupportSQLiteQuery
import androidx.room.RawQuery
import androidx.sqlite.db.SimpleSQLiteQuery
import androidx.room.OnConflictStrategy
import androidx.room.Dao


@Dao
abstract class CacheBaseDao<T>(private val tableName: String) {

    open fun getAll(): List<T> {
        val query =
            SimpleSQLiteQuery("select * from $tableName where deleteFlag = 0 order by sortKey")
        return doGetAll(query)
    }

    open fun getById(id: Int): T {
        val query = SimpleSQLiteQuery(
            "select * from $tableName where id = ?",
            arrayOf<Any>(id)
        )
        return doGetById(query)
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insert(entity: T)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertAll(entities: List<T>)

    fun deleteAll(): Int {
        val query = SimpleSQLiteQuery("delete from $tableName")
        return doDeleteAll(query)
    }

    fun itemsCount(): Int {
        val query = SimpleSQLiteQuery("select count(*) from $tableName")
        return doDeleteAll(query)
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
