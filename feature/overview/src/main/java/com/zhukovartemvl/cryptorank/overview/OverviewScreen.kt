package com.zhukovartemvl.cryptorank.overview

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zhukovartemvl.cryptorank.core.model.CoinDTO
import com.zhukovartemvl.cryptorank.overview.component.CoinItem

@Composable
fun OverviewScreen(
    navController: NavController,
    viewModel: OverviewScreenViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(stringResource(id = R.string.app_name))
        })
    }) {
        CoinRankList(state.coins)
    }
}

@Composable
private fun CoinRankList(coins: List<CoinDTO>) {
    Column {
        CoinRankListHeader(
            onCryptocurrencyClick = {},
            onPriceClick = {},
            onSparklineClick = {}
        )
        LazyColumn {
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
        }
    }
}

@Composable
private fun CoinRankListHeader(
    onCryptocurrencyClick: () -> Unit,
    onPriceClick: () -> Unit,
    onSparklineClick: () -> Unit
) {
    Row {
        Text(
            modifier = Modifier.weight(5f),
            text = "Cryptocurrency",
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.weight(4f),
            text = "Price",
            textAlign = TextAlign.Start
        )
        Text(
            modifier = Modifier.weight(2f),
            text = "24H",
            textAlign = TextAlign.End
        )
    }
}