package com.juliwong.android.sunnyweather.logic.model.weather

/**
 * @author JuliWong
 * @date 2021/3/17
 * 封装Realtime和Daily对象
 */
data class Weather(val realtime: RealtimeResponse.Realtime, val daily: DailyResponse.Daily)
