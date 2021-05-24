package com.zhukovartemvl.cryptorank.overview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zhukovartemvl.cryptorank.overview.OverviewScreenContract.*
import com.zhukovartemvl.cryptorank.core_ui.theme.CryptoRankText
import com.zhukovartemvl.cryptorank.core_ui.theme.TriangleShape
import com.zhukovartemvl.cryptorank.core_ui.theme.TriangleShapeDirection


@Composable
fun CoinRankListHeader(
    listOrder: ListOrder,
    onCryptocurrencyClick: () -> Unit,
    onPriceClick: () -> Unit,
    onSparklineClick: () -> Unit
) {
    Row(modifier = Modifier) {
        TitleText(
            modifier = Modifier.weight(5f),
            ascending = if (listOrder is ListOrder.MarketCap) listOrder.ascending else null,
            text = "Cryptocurrency",
            onClick = onCryptocurrencyClick
        )
        TitleText(
            modifier = Modifier.weight(2f),
            ascending = if (listOrder is ListOrder.Price) listOrder.ascending else null,
            text = "Price",
            onClick = onPriceClick
        )
        TitleText(
            modifier = Modifier.weight(2f),
            ascending = if (listOrder is ListOrder.DayChange) listOrder.ascending else null,
            text = "24H",
            onClick = onSparklineClick
        )
    }
}

@Composable
private fun TitleText(
    text: String,
    ascending: Boolean? = null,
    modifier: Modifier,
    onClick: () -> Unit
) {
    Row(modifier = modifier.clickable(onClick = onClick)) {
        Text(
            modifier = Modifier.padding(10.dp),
            text = text,
            textAlign = TextAlign.Center,
            style = CryptoRankText.ItemTitleText
        )
        if (ascending != null) {
            val triangleShapeDirection =
                if (ascending) TriangleShapeDirection.Top
                else TriangleShapeDirection.Bottom
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(shape = TriangleShape(triangleShapeDirection))
                    .align(Alignment.CenterVertically)
                    .background(Color.Black)
            )
        }
    }
}
