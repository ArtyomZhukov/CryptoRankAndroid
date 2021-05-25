package com.zhukovartemvl.cryptorank.overview.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.zhukovartemvl.cryptorank.core_ui.theme.ColorDarkGrey
import com.zhukovartemvl.cryptorank.overview.OverviewScreenContract.*
import com.zhukovartemvl.cryptorank.core_ui.theme.CryptoRankText
import com.zhukovartemvl.cryptorank.core_ui.theme.TriangleShape
import com.zhukovartemvl.cryptorank.core_ui.theme.TriangleShapeDirection
import com.zhukovartemvl.cryptorank.resources.R


@Composable
fun CoinRankListHeader(
    listOrder: ListOrder? = null,
    onCryptocurrencyClick: () -> Unit = {},
    onPriceClick: () -> Unit = {},
    onSparklineClick: () -> Unit = {}
) {
    Card(elevation = 4.dp) {
        Row(modifier = Modifier) {
            TitleButton(
                modifier = Modifier.weight(5f),
                ascending = if (listOrder is ListOrder.MarketCap) listOrder.ascending else null,
                text = stringResource(id = R.string.cryptocurrency),
                onClick = onCryptocurrencyClick
            )
            TitleButton(
                modifier = Modifier.weight(2f),
                ascending = if (listOrder is ListOrder.Price) listOrder.ascending else null,
                text = stringResource(id = R.string.price),
                onClick = onPriceClick
            )
            TitleButton(
                modifier = Modifier.weight(2f),
                ascending = if (listOrder is ListOrder.DayChange) listOrder.ascending else null,
                text = stringResource(id = R.string.day_change),
                onClick = onSparklineClick
            )
        }
    }
}

@Composable
private fun TitleButton(
    text: String,
    ascending: Boolean? = null,
    modifier: Modifier,
    onClick: () -> Unit
) {
    TextButton(modifier = modifier, onClick = onClick) {
        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = text,
                textAlign = TextAlign.Center,
                style = CryptoRankText.ItemTitleText
            )
            if (ascending != null) {
                val triangleShapeDirection =
                    if (ascending) TriangleShapeDirection.Bottom
                    else TriangleShapeDirection.Top
                Box(
                    modifier = Modifier
                        .padding(start = 10.dp)
                        .size(8.dp)
                        .clip(shape = TriangleShape(triangleShapeDirection))
                        .align(Alignment.CenterVertically)
                        .background(color = ColorDarkGrey)
                )
            }
        }
    }
}
