package com.juliwong.android.sunnyweather

import android.annotation.SuppressLint
import android.app.Application
import android.content.Context

/**
 * @author JuliWong
 * @date 2021/3/16
 * 便于提供全局Context
 */
class SunnyWeatherApplication : Application() {
    companion object {

        // 彩云天气令牌token值
        const val TOKEN = ""

        @SuppressLint("StaticFieldLeak")
        lateinit var context: Context
    }

    override fun onCreate() {
        super.onCreate()
        context = applicationContext
    }
}