package com.zhukovartemvl.cryptorank.overview.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color


@Composable
internal fun CoinItem(
    rank: Int,
    name: String,
    symbol: String,
    iconUrl: String,
    price: Float,
    marketCap: Float,
    change: Float,
    sparklineItems: List<Float>
) {
    Row(modifier = Modifier.height(60.dp), verticalAlignment = Alignment.CenterVertically) {
        Row(modifier = Modifier.weight(5f)) {
            Text(text = rank.toString())
            //TODO: Icon
            Column {
                Text(text = name)
                Text(text = symbol)
            }
        }
        Row(modifier = Modifier.weight(4f)) {
            Column {
                Text(text = price.toPriceString())
                Text(text = marketCap.toPriceString())
            }
        }
        Row(modifier = Modifier.weight(2f)) {

            val color = if (change >= 0) Color.Green else Color.Red


            Column {
                Text(text = change.toPercentString(), color = color)
                Sparkline(
                    modifier = Modifier.fillMaxSize(),
                    lineColor = color,
                    values = sparklineItems
                )
            }
        }
    }
}

private fun Float.toPriceString() = "$ " + "%.2f".format(this)

private fun Float.toPercentString() = "%.2f".format(this) + "%"