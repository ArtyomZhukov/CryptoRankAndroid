package com.zhukovartemvl.cryptorank.overview.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.google.accompanist.glide.rememberGlidePainter
import com.zhukovartemvl.cryptorank.core_ui.theme.CryptoRankText


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
    Card(elevation = 4.dp) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .padding(vertical = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(modifier = Modifier.weight(5f)) {
                Text(
                    modifier = Modifier
                        .width(30.dp)
                        .align(Alignment.CenterVertically),
                    text = rank.toString(),
                    textAlign = TextAlign.Center,
                    style = CryptoRankText.ItemText
                )
                Image(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(32.dp),
                    painter = rememberGlidePainter(request = iconUrl),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
                Column {
                    Text(
                        text = name,
                        style = CryptoRankText.ItemTitleText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = symbol,
                        style = CryptoRankText.ItemText
                    )
                }
            }
            Row(modifier = Modifier.weight(2f)) {
                Column {
                    Text(
                        text = price.toPriceString(),
                        style = CryptoRankText.ItemTitleText
                    )
                    Text(
                        text = marketCap.toMarketCapString(),
                        style = CryptoRankText.ItemText
                    )
                }
            }
            Row(modifier = Modifier.weight(2f)) {
                val color = if (change >= 0) Color.Green else Color.Red

                Column {
                    Text(
                        text = change.toPercentString(),
                        modifier = Modifier.fillMaxWidth(),
                        color = color,
                        style = CryptoRankText.ItemTitleText,
                        textAlign = TextAlign.Center
                    )
                    Sparkline(
                        modifier = Modifier.fillMaxSize(),
                        lineColor = color,
                        values = sparklineItems
                    )
                }
            }
        }
    }
}

@Composable
private fun PrimaryText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 16.sp,
            color = Color.Black,
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
private fun SecondaryText(text: String) {
    Text(
        text = text,
        style = TextStyle(
            fontSize = 15.sp,
            color = Color.Gray,
            fontWeight = FontWeight.Normal
        )
    )
}

private fun Float.toPriceString() = "$ " + "%.2f".format(this)
private fun Float.toMarketCapString(): String {
    val (number, suffix) = when {
        this > trillion.first -> Pair(this / trillion.first, trillion.second)
        this > billion.first -> Pair(this / billion.first, billion.second)
        this > million.first -> Pair(this / million.first, million.second)
        this > thousand.first -> Pair(this / thousand.first, thousand.second)
        else -> Pair(this, "")
    }
    return "$ " + "%.2f".format(number) + suffix
}

private val trillion = Pair(1_000_000_000_000f, "T")
private val billion = Pair(1_000_000_000f, "B")
private val million = Pair(1_000_000f, "M")
private val thousand = Pair(1_000f, "K")

private fun Float.toPercentString() = "%.2f".format(this) + "%"
