package com.juliwong.android.sunnyweather.logic.network

import com.juliwong.android.sunnyweather.SunnyWeatherApplication
import com.juliwong.android.sunnyweather.logic.model.place.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author JuliWong
 * @data 2021/3/16
 * Retrofit对常用的Http请求类型都进行了支持 @GET，@POST，@PUT，@PATCH，@DELETE
 * 根路径为：https://api.caiyunapp.com/
 */
interface PlaceService {

    /**
     * 接口地址带参数：GET https://api.caiyunapp.com/v2.5/place?query=<query>&token=<token>&lang=zh_CN
     */
    @GET("v2/place?token=${SunnyWeatherApplication.TOKEN}&lang=zh_CN")
    fun searchPlaces(@Query("query") query: String): Call<PlaceResponse>
}