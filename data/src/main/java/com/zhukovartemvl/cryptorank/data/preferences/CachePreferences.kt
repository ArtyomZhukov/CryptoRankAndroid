package com.zhukovartemvl.cryptorank.data.preferences


interface CachePreferences {

    fun getLongValueByKey(key: String, default: Long = 0L): Long
    fun setLongValueByKey(key: String, value: Long)

}
