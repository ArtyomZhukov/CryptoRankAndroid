package com.zhukovartemvl.cryptorank.data.preferences.impl

import android.content.Context
import android.content.SharedPreferences
import com.zhukovartemvl.cryptorank.data.preferences.CachePreferences


class CachePreferencesImpl(context: Context) : CachePreferences {

    private var preferences: SharedPreferences =
        context.getSharedPreferences("cache_preferences", Context.MODE_PRIVATE)

    override fun getLongValueByKey(key: String, default: Long): Long {
        return preferences.getLong(key, default)
    }

    override fun setLongValueByKey(key: String, value: Long) {
        preferences.edit().putLong(key, value).apply()
    }

}
