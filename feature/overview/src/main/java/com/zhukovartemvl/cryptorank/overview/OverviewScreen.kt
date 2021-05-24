package com.zhukovartemvl.cryptorank.overview

import android.widget.Toast
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zhukovartemvl.cryptorank.overview.component.CoinItem
import com.zhukovartemvl.cryptorank.overview.OverviewScreenContract.*
import com.zhukovartemvl.cryptorank.overview.component.CoinRankListHeader


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun OverviewScreen(
    navController: NavController,
    viewModel: OverviewScreenViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val action by viewModel.action.collectAsState(initial = Action.None)

    when (val collectedAction = action) {
        is Action.Error -> {
            val message = collectedAction.error.localizedMessage
            Toast.makeText(LocalContext.current, message, Toast.LENGTH_SHORT).show()
        }
        Action.None -> Unit
    }


    Scaffold(topBar = {
        TopAppBar(title = {
            Text(stringResource(id = R.string.app_name))
        })
    }) {
        when (val screenState = state.screenState) {
            ScreenState.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
            ScreenState.Default -> {
                LazyColumn {
                    stickyHeader {
                        CoinRankListHeader(
                            listOrder = state.listOrder,
                            onCryptocurrencyClick = { viewModel.setEvent(Event.OnCryptocurrencyClicked) },
                            onPriceClick = { viewModel.setEvent(Event.OnPriceClicked) },
                            onSparklineClick = { viewModel.setEvent(Event.OnDayChangeClicked) }
                        )
                    }
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
            is ScreenState.Error -> {
                Text(screenState.errorMessage)
            }
            ScreenState.Refreshing -> {

            }
        }

    }
}
