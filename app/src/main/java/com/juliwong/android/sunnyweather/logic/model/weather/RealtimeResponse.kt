package com.juliwong.android.sunnyweather.logic.model.weather

import com.google.gson.annotations.SerializedName

/**
 * @author JuliWong
 * @date 2021/3/17
 * 数据模型都定义在RealtimeResponse内部，可以防止出现与其它接口的数据模型同名的冲突
 */
data class RealtimeResponse(val status: String, val result: Result) {

    data class Result(val realtime: Realtime)

    data class Realtime(
        val temperature: Float,
        val skycon: String,
        @SerializedName("air_quality") val airQuality: AirQuality
    )

    data class AirQuality(val aqi: AQI)

    data class AQI(val chn: Float)
}