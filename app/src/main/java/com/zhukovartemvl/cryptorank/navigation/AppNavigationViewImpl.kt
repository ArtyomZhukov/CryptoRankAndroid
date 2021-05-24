package com.zhukovartemvl.cryptorank.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.zhukovartemvl.cryptorank.core_ui.navigation.AppNavigationParams
import com.zhukovartemvl.cryptorank.core_ui.navigation.AppNavigationView
import com.zhukovartemvl.cryptorank.overview.OverviewScreen
import com.zhukovartemvl.cryptorank.core_ui.theme.CryptoRankTheme


class AppNavigationViewImpl : AppNavigationView {

    override fun content(context: Context) = ComposeView(context).apply {
        setContent { CryptoRankTheme { NavigationHost() } }
    }

    @Composable
    private fun NavigationHost() {
        val navController = rememberNavController()

        NavHost(
            navController = navController,
            startDestination = AppNavigationParams.Screen.Overview
        ) {
            composable(AppNavigationParams.Screen.Overview) {
                OverviewScreen(navController)
            }
        }
    }
}
