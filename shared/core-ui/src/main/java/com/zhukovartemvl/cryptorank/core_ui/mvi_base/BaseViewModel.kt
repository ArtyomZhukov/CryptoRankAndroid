package com.zhukovartemvl.cryptorank.core_ui.mvi_base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch


abstract class BaseViewModel<Event : UiEvent, State : UiState, Action : UiAction> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState = _uiState.asStateFlow()

    private val _event: MutableSharedFlow<Event> = MutableSharedFlow()
    val event = _event.asSharedFlow()

    private val _action: Channel<Action> = Channel()
    val action = _action.receiveAsFlow()

    init { subscribeEvents() }

    /* Start listening to Event */
    private fun subscribeEvents() { viewModelScope.launch { event.collect { handleEvent(it) } } }

    /* Handle each event */
    abstract fun handleEvent(event: Event)

    /* Set new Event */
    fun setEvent(event: Event) {
        val newEvent = event
        viewModelScope.launch { _event.emit(newEvent) }
    }
    /* Set new Ui State */
    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    /* Set new Action */
    protected fun setAction(builder: () -> Action) {
        val actionValue = builder()
        viewModelScope.launch { _action.send(actionValue) }
    }
}
