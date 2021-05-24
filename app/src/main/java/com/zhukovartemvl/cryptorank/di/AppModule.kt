package com.zhukovartemvl.cryptorank.di

import com.zhukovartemvl.cryptorank.core_ui.navigation.AppNavigationView
import com.zhukovartemvl.cryptorank.navigation.AppNavigationViewImpl
import org.koin.dsl.module


val appModule = module {
    single { AppNavigationViewImpl() as AppNavigationView }
}
