package com.juliwong.android.sunnyweather.logic.network

import com.juliwong.android.sunnyweather.SunnyWeatherApplication
import com.juliwong.android.sunnyweather.logic.model.weather.DailyResponse
import com.juliwong.android.sunnyweather.logic.model.weather.RealtimeResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

/**
 * @author JuliWong
 * @data 2021/3/17
 * Retrofit对常用的Http请求类型都进行了支持 @GET，@POST，@PUT，@PATCH，@DELETE
 * 根路径为：https://api.caiyunapp.com/
 */
interface WeatherService {

    /**
     * 接口地址中部分内容的动态变化的：GET https://api.caiyunapp.com/v2.5/<token>/<lng>,<lat>/realtime.json
     */
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/realtime.json")
    fun getRealtimeWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<RealtimeResponse>

    /**
     * 接口地址中部分内容的动态变化的：GET https://api.caiyunapp.com/v2.5/<token>/<lng>,<lat>/daily.json
     */
    @GET("v2.5/${SunnyWeatherApplication.TOKEN}/{lng},{lat}/daily.json")
    fun getDailyWeather(
        @Path("lng") lng: String,
        @Path("lat") lat: String
    ): Call<DailyResponse>
}