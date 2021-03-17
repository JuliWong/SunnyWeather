package com.juliwong.android.sunnyweather.logic

import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.juliwong.android.sunnyweather.logic.model.Place
import com.juliwong.android.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import java.lang.RuntimeException

/**
 * @author JuliWong
 * @date 2021/3/16
 * 仓库层的统一封装入口
 */
object Repository {
    fun searchPlaces(query: String) = liveData(Dispatchers.IO) {
        val result = try {
            val placeResponse = SunnyWeatherNetwork.searchPlaces(query)
            if (placeResponse.status == "ok") {
                val places = placeResponse.places
                Result.success(places)
            } else {
                Result.failure(RuntimeException("response status is ${placeResponse.status}"))
            }
        } catch (e: Exception) {
            Result.failure<List<Place>>(e)
        }
        emit(result)
    }
}