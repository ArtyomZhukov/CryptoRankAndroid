package com.zhukovartemvl.cryptorank.overview.component

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


@Composable
fun CoinShimmers() {
    Column {
        repeat(20) {
            CoinShimmerItem()
        }
    }
}

@Composable
private fun CoinShimmerItem() {
    val transition = rememberInfiniteTransition()
    val translateAnim by transition.animateFloat(
        initialValue = 100f,
        targetValue = 3000f,
        animationSpec = infiniteRepeatable(
            tween(durationMillis = 1200, easing = LinearEasing),
            RepeatMode.Restart
        )
    )

    val colors = listOf(Color.LightGray.copy(alpha = 0.6f), Color.LightGray)
    val brush = Brush.horizontalGradient(colors, 0f, translateAnim.dp.value)
    Card(elevation = 4.dp) {
        Row(
            modifier = Modifier
                .height(60.dp)
                .padding(5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .padding(6.dp)
                    .padding(start = 25.dp)
                    .size(32.dp)
                    .background(brush = brush, shape = CircleShape)
            )
            Box(
                modifier = Modifier
                    .weight(1f)
                    .height(32.dp)
                    .background(brush = brush, shape = RoundedCornerShape(size = 10.dp))
            )
        }
    }
}

@Preview
@Composable
private fun CoinShimmersPreview() {
    CoinShimmers()
}
