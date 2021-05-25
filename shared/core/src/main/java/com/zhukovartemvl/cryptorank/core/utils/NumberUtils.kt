package com.zhukovartemvl.cryptorank.core.utils


fun Float.toPriceString() = "$ " + "%.2f".format(this)

fun Float.toPercentString() = (if (this > 0) "+" else "") + "%.2f".format(this) + "%"

fun Float.toMarketCapString(): String {
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
