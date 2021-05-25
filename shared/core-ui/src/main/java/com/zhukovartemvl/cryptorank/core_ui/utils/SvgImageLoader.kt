package com.zhukovartemvl.cryptorank.core_ui.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.decode.SvgDecoder


@Composable
fun getSvgImageLoader(): ImageLoader {
    val context = LocalContext.current
    return ImageLoader.Builder(context)
        .componentRegistry { add(SvgDecoder(context)) }
        .build()
}
