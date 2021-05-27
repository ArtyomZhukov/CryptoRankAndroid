package com.zhukovartemvl.cryptorank.data.converter

import com.zhukovartemvl.cryptorank.core.model.CoinDTO
import com.zhukovartemvl.cryptorank.data.entity.CoinEntity


fun CoinEntity.transform(): CoinDTO {
    return CoinDTO(
        uuid = uuid,
        name = name,
        symbol = symbol,
        iconUrl = iconUrl,
        price = price,
        marketCap = marketCap,
        change = change ?: 0f,
        rank = rank,
        sparkline = sparkline.filterNotNull()
    )
}
