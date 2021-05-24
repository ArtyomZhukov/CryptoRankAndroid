package com.zhukovartemvl.cryptorank.overview

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.zhukovartemvl.cryptorank.core.model.CoinDTO
import com.zhukovartemvl.cryptorank.overview.component.CoinItem
import com.zhukovartemvl.cryptorank.overview.OverviewScreenContract.*
import com.zhukovartemvl.cryptorank.overview.component.CoinRankListHeader


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
//        val isRefreshing = state.screenState == ScreenState.Refreshing
//        val swipeRefreshState = rememberSwipeRefreshState(isRefreshing)
//
//        SwipeRefresh(
//            state = swipeRefreshState,
//            onRefresh = { viewModel.setEvent(Event.Refresh) },
//        ) {
        Column {
            CoinRankListHeader(
                listOrder = state.listOrder,
                onCryptocurrencyClick = { viewModel.setEvent(Event.OnCryptocurrencyClicked) },
                onPriceClick = { viewModel.setEvent(Event.OnPriceClicked) },
                onSparklineClick = { viewModel.setEvent(Event.OnDayChangeClicked) }
            )
            LazyColumn {
                items(items = state.coins) { coinItem ->
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
}
