package com.zhukovartemvl.cryptorank.data.converter

import androidx.room.TypeConverter
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json


object SparklineConverter {
    @TypeConverter
    fun fromString(value: String): List<Float> {
        return Json.decodeFromString(CoinSparkline.serializer(), value).value
    }

    @TypeConverter
    fun fromArrayList(sparkline: List<Float>): String {
        return Json.encodeToString(CoinSparkline.serializer(), CoinSparkline(sparkline))
    }
}

@Serializable
data class CoinSparkline(val value: List<Float>)