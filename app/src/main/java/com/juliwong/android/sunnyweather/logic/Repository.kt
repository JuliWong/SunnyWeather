package com.juliwong.android.sunnyweather.logic

import android.content.Context
import androidx.lifecycle.liveData
import com.juliwong.android.sunnyweather.logic.dao.PlaceDao
import com.juliwong.android.sunnyweather.logic.model.place.Place
import com.juliwong.android.sunnyweather.logic.model.weather.Weather
import com.juliwong.android.sunnyweather.logic.network.SunnyWeatherNetwork
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import java.lang.RuntimeException
import kotlin.coroutines.CoroutineContext

/**
 * @author JuliWong
 * @date 2021/3/16
 * 仓库层的统一封装入口
 */
object Repository {
    /**
     * 写法一：不使用封装好的fire()
     */
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

    /**
     * 写法二：使用封装好的fire()
     * 将getRealtimeWeather()和getDailyWeather()进一步封装
     */
    fun refreshWeather(lng: String, lat: String) = fire(Dispatchers.IO) {
        // 创建协程作用域
        coroutineScope {
            // 两个网络请求
            val deferredRealtime = async { SunnyWeatherNetwork.getRealtimeWeather(lng, lat) }
            val deferredDaily = async { SunnyWeatherNetwork.getDailyWeather(lng, lat) }
            val realtimeResponse = deferredRealtime.await()
            val dailyResponse = deferredDaily.await()
            if (realtimeResponse.status == "ok" && dailyResponse.status == "ok") {
                val weather =
                    Weather(realtimeResponse.result.realtime, dailyResponse.result.daily)
                Result.success(weather)
            } else {
                Result.failure(RuntimeException("realtime response status is ${realtimeResponse.status}" + "daily response status is ${dailyResponse.status}"))
            }
        }
    }

    // 在统一的入口函数中，封装每个网络请求都要进行的try catch处理，简化代码
    private fun <T> fire(context: CoroutineContext, block: suspend () -> Result<T>) =
        liveData<Result<T>>(context) {
            val result = try {
                block()
            } catch (e: Exception) {
                Result.failure<T>(e)
            }
            emit(result)
        }

    // 关于保存搜索过的Place,接口封装
    fun savePlace(place: Place) = PlaceDao.savePlace(place)
    fun getSavedPlace() = PlaceDao.getSavedPlace()
    fun isPlaceSaved() = PlaceDao.isPlaceSaved()
}