package com.zhukovartemvl.cryptorank.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.zhukovartemvl.cryptorank.data.converter.SparklineConverter
import com.zhukovartemvl.cryptorank.data.database.dao.CoinCacheDao
import com.zhukovartemvl.cryptorank.data.entity.CoinEntity


@Database(entities = [CoinEntity::class], version = 1, exportSchema = false)
@TypeConverters(SparklineConverter::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun coinCacheDao(): CoinCacheDao

}
