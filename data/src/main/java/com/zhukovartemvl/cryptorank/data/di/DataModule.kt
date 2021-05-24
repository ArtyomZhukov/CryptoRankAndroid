package com.zhukovartemvl.cryptorank.data.di

import android.content.Context
import androidx.room.Room
import com.zhukovartemvl.cryptorank.core.repository.CoinRepository
import com.zhukovartemvl.cryptorank.data.R
import com.zhukovartemvl.cryptorank.data.cache.CoinsCache
import com.zhukovartemvl.cryptorank.data.cache.impl.CoinsCacheImpl
import com.zhukovartemvl.cryptorank.data.database.AppDatabase
import com.zhukovartemvl.cryptorank.data.network.api.CoinApi
import com.zhukovartemvl.cryptorank.data.network.api.impl.CoinApiImpl
import com.zhukovartemvl.cryptorank.data.preferences.CachePreferences
import com.zhukovartemvl.cryptorank.data.preferences.impl.CachePreferencesImpl
import com.zhukovartemvl.cryptorank.data.repository.CoinRepositoryImpl
import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import kotlinx.serialization.json.Json
import org.koin.dsl.module


val dataModule = module {

    single { getDatabase(get()) }
    factory { CoinRepositoryImpl(get(), get()) as CoinRepository }

    single { createJson() }
    single { createHttpClient(get()) }
    factory { CoinApiImpl(get()) as CoinApi }

    factory { CachePreferencesImpl(get()) as CachePreferences }

    factory { CoinsCacheImpl(get(), get()) as CoinsCache }
}

private fun getDatabase(context: Context) =
    Room.databaseBuilder(context, AppDatabase::class.java, context.getString(R.string.db_name))
        .fallbackToDestructiveMigration()
        .build()

private fun createJson() = Json { isLenient = true; ignoreUnknownKeys = true }

private fun createHttpClient(json: Json) = HttpClient {
    install(JsonFeature) {
        serializer = KotlinxSerializer(json)
    }
}
