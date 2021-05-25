package com.zhukovartemvl.cryptorank.overview

import com.zhukovartemvl.cryptorank.core.model.CoinDTO
import com.zhukovartemvl.cryptorank.core_ui.mvi_base.UiAction
import com.zhukovartemvl.cryptorank.core_ui.mvi_base.UiEvent
import com.zhukovartemvl.cryptorank.core_ui.mvi_base.UiState


class OverviewScreenContract {

    data class State(
        val coins: List<CoinDTO>,
        val screenState: ScreenState,
        val listOrder: ListOrder
    ) : UiState

    sealed class ScreenState {
        object Loading : ScreenState()
        object Default : ScreenState()
        object Sorting : ScreenState()
        object Refreshing : ScreenState()
        data class Error(val errorMessage: String) : ScreenState()
    }

    sealed class ListOrder(var ascending: Boolean) {
        class MarketCap(ascending: Boolean = false) : ListOrder(ascending)
        class Price(ascending: Boolean = false) : ListOrder(ascending)
        class DayChange(ascending: Boolean = false) : ListOrder(ascending)
    }

    sealed class Event : UiEvent {
        object OnCryptocurrencyClicked : Event()
        object OnPriceClicked : Event()
        object OnDayChangeClicked : Event()
        object Refresh : Event()
    }

    sealed class Action : UiAction {
        object None : Action()
        data class Error(val error: Exception) : Action()
    }

}
