package com.zhukovartemvl.cryptorank.overview

import android.widget.Toast
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.zhukovartemvl.cryptorank.overview.OverviewScreenContract.*
import com.zhukovartemvl.cryptorank.overview.component.CoinRankListHeader
import com.zhukovartemvl.cryptorank.overview.component.CoinShimmers
import com.zhukovartemvl.cryptorank.overview.component.CoinsList


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
            ScreenState.Loading -> CoinShimmers()
            ScreenState.Default -> CoinsList(
                coins = state.coins,
                listOrder = state.listOrder,
                onCryptocurrencyClick = { viewModel.setEvent(Event.OnCryptocurrencyClicked) },
                onPriceClick = { viewModel.setEvent(Event.OnPriceClicked) },
                onSparklineClick = { viewModel.setEvent(Event.OnDayChangeClicked) }
            )
            is ScreenState.Error -> {
                Text(screenState.errorMessage)
            }
            ScreenState.Sorting -> CoinRankListHeader()
            ScreenState.Refreshing -> Unit
        }
    }
}
