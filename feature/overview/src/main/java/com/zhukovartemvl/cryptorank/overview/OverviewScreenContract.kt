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
        data class Refresh(val forceLoad: Boolean) : Event()
    }

    sealed class Action : UiAction {
        data class Error(val error: Exception) : Action()
    }

}
