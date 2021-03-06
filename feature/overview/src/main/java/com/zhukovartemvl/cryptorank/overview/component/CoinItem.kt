package com.zhukovartemvl.cryptorank.overview.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.google.accompanist.coil.rememberCoilPainter
import com.zhukovartemvl.cryptorank.core.utils.toMarketCapString
import com.zhukovartemvl.cryptorank.core.utils.toPercentString
import com.zhukovartemvl.cryptorank.core.utils.toPriceString
import com.zhukovartemvl.cryptorank.core_ui.theme.ColorGreen
import com.zhukovartemvl.cryptorank.core_ui.theme.ColorRed
import com.zhukovartemvl.cryptorank.core_ui.theme.CryptoRankText
import com.zhukovartemvl.cryptorank.core_ui.utils.getSvgImageLoader


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
                    style = CryptoRankText.SecondaryItemText
                )
                Image(
                    modifier = Modifier
                        .padding(6.dp)
                        .size(32.dp)
                        .padding(end = 4.dp),
                    painter = rememberCoilPainter(
                        request = iconUrl,
                        imageLoader = getSvgImageLoader()
                    ),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )

                Column {
                    Text(
                        text = name,
                        style = CryptoRankText.ItemText,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                    Text(
                        text = symbol,
                        style = CryptoRankText.SecondaryItemText
                    )
                }
            }
            Row(modifier = Modifier.weight(2f)) {
                Column {
                    Text(
                        text = price.toPriceString(),
                        style = CryptoRankText.ItemText
                    )
                    Text(
                        text = marketCap.toMarketCapString(),
                        style = CryptoRankText.SecondaryItemText
                    )
                }
            }
            Row(modifier = Modifier.weight(2f)) {
                val color = if (change >= 0) ColorGreen else ColorRed

                Column {
                    Text(
                        text = change.toPercentString(),
                        modifier = Modifier.fillMaxWidth(),
                        color = color,
                        style = CryptoRankText.ItemText,
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
