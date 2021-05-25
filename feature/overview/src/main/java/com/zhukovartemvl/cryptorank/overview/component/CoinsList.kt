package com.zhukovartemvl.cryptorank.overview.component

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.zhukovartemvl.cryptorank.core.model.CoinDTO
import com.zhukovartemvl.cryptorank.overview.OverviewScreenContract


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CoinsList(
    coins: List<CoinDTO>,
    listOrder: OverviewScreenContract.ListOrder,
    onCryptocurrencyClick: () -> Unit,
    onPriceClick: () -> Unit,
    onSparklineClick: () -> Unit
) {
    LazyColumn {
        stickyHeader {
            CoinRankListHeader(
                listOrder = listOrder,
                onCryptocurrencyClick = onCryptocurrencyClick,
                onPriceClick = onPriceClick,
                onSparklineClick = onSparklineClick
            )
        }
        items(items = coins) { coinItem ->
            CoinItem(
                rank = coinItem.rank,
                name = coinItem.name,
                symbol = coinItem.symbol,
                iconUrl = coinItem.iconUrl,
                price = coinItem.price,
                marketCap = coinItem.marketCap,
                change = coinItem.change,
                sparklineItems = coinItem.sparkline
            )
        }
        item {
            Spacer(modifier = Modifier.height(60.dp))
        }
    }
}
