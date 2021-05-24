package com.zhukovartemvl.cryptorank.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
@Entity(tableName = "coin")
data class CoinEntity(
    @SerialName("uuid") val uuid: String,
    @SerialName("name") val name: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("iconUrl") val iconUrl: String,
    @SerialName("price") val price: Float,
    @SerialName("marketCap") val marketCap: Float,
    @SerialName("change") val change: Float,
    @SerialName("rank") val rank: Int,
    @SerialName("sparkline") val sparkline: List<Float>
) {
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}
