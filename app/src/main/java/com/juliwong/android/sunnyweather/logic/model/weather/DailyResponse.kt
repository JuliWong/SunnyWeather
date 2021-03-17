package com.juliwong.android.sunnyweather.logic.model.weather

import com.google.gson.annotations.SerializedName
import java.util.*

/**
 * @author JuliWong
 * @date 2021/3/17
 */
data class DailyResponse(val status: String, val result: Result) {
    data class Result(val daily: Daily)
    data class Daily(
        val temperature: List<Temperature>,
        val skycon: List<Skycon>,
        @SerializedName("life_index") val lifeIndex: LifeIndex
    )

    data class Temperature(val max: Float, val min: Float)

    // 注：天气情况数据，如CLOUDY，WIND，后续编写了一个转换函数，将其转换成Sky对象
    data class Skycon(val value: String, val date: Date)

    data class LifeIndex(
        val coldRisk: List<LifeDescription>,
        val carWashing: List<LifeDescription>,
        val ultraviolet: List<LifeDescription>,
        val dressing: List<LifeDescription>
    )

    data class LifeDescription(val desc: String)
}