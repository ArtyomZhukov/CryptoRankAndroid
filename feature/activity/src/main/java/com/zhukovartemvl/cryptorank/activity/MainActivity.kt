package com.zhukovartemvl.cryptorank.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import com.zhukovartemvl.cryptorank.core_ui.navigation.AppNavigationView
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    private val appView: AppNavigationView by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(appView.content(this))
    }
}
