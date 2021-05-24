package com.zhukovartemvl.cryptorank.core_ui.navigation

import android.content.Context
import androidx.compose.ui.platform.ComposeView


interface AppNavigationView {
    fun content(context: Context): ComposeView
}
