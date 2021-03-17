package com.juliwong.android.sunnyweather.logic.dao

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.juliwong.android.sunnyweather.SunnyWeatherApplication
import com.juliwong.android.sunnyweather.logic.model.place.Place

/**
 * @author JuliWong
 * @date 2021/3/17
 * 封装存储和读取数据的接口
 */
object PlaceDao {

    fun isPlaceSaved() = sharedPreferences().contains("place")

    fun savePlace(place: Place) {
        sharedPreferences().edit {
            putString("place", Gson().toJson(place))
        }
    }

    fun getSavedPlace(): Place {
        val placeJson = sharedPreferences().getString("place", "")
        return Gson().fromJson(placeJson, Place::class.java)
    }

    private fun sharedPreferences() =
        SunnyWeatherApplication.context.getSharedPreferences("sunny_weather", Context.MODE_PRIVATE)

}