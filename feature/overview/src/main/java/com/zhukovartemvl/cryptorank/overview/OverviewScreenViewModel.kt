package com.zhukovartemvl.cryptorank.overview

import androidx.lifecycle.viewModelScope
import com.zhukovartemvl.cryptorank.core.model.CoinDTO
import com.zhukovartemvl.cryptorank.core.repository.CoinRepository
import com.zhukovartemvl.cryptorank.core.utils.doOnFailure
import com.zhukovartemvl.cryptorank.core.utils.doOnSuccess
import com.zhukovartemvl.cryptorank.core_ui.mvi_base.BaseViewModel
import com.zhukovartemvl.cryptorank.overview.OverviewScreenContract.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class OverviewScreenViewModel : BaseViewModel<Event, State, Action>(), KoinComponent {

    private val coinRepository: CoinRepository by inject()

    init {
        if (currentState.screenState == ScreenState.Loading) {
            loadCoins()
        }
    }

    override fun createInitialState() = State(
        coins = listOf(),
        screenState = ScreenState.Loading,
        listOrder = ListOrder.MarketCap(ascending = false)
    )

    override fun handleEvent(event: Event) {
        when (event) {
            Event.OnCryptocurrencyClicked -> {
                changeListOrder(ListOrder.MarketCap())
            }
            Event.OnDayChangeClicked -> {
                changeListOrder(ListOrder.DayChange())
            }
            Event.OnPriceClicked -> {
                changeListOrder(ListOrder.Price())
            }
            is Event.Refresh -> {
                loadCoins(true)
            }
        }
    }

    private fun loadCoins(force: Boolean = false) = viewModelScope.launch(Dispatchers.IO) {
        coinRepository.fetchCoins(force)
            .doOnSuccess { coins ->
                setState {
                    copy(
                        coins = coins.sort(listOrder),
                        screenState = ScreenState.Default
                    )
                }
            }.doOnFailure { error ->
                setAction { Action.Error(error) }
            }
    }

    private fun changeListOrder(listOrder: ListOrder) {
        if (currentState.listOrder == listOrder) {
            listOrder.apply { ascending = !ascending }
        }

        val coins = currentState.coins
        setState {
            copy(
                coins = coins.sort(listOrder),
                listOrder = listOrder
            )
        }
    }

    private fun List<CoinDTO>.sort(listOrder: ListOrder): List<CoinDTO> {
        return if (listOrder.ascending)
            this.sortedBy { it.getSortingParam(listOrder) }
        else
            this.sortedByDescending { it.getSortingParam(listOrder) }
    }

    private fun CoinDTO.getSortingParam(listOrder: ListOrder) = when (listOrder) {
        is ListOrder.MarketCap -> marketCap
        is ListOrder.Price -> price
        is ListOrder.DayChange -> change
    }

}
