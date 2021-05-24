package com.zhukovartemvl.cryptorank.core_ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

object CryptoRankText {
    val Title = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 18.sp,
        color = Color.Black
    )
    val ItemTitleText = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = 16.sp,
        color = Color.Black
    )
    val ItemText = TextStyle(
        fontSize = 16.sp,
        color = Color.Black
    )
    val SecondaryItemText = TextStyle(
        fontSize = 16.sp,
        color = Color.Gray
    )
}
