package com.juliwong.android.sunnyweather.logic.network

import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.lang.RuntimeException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

object SunnyWeatherNetwork {

    // 1.创建PlaceService接口的动态代理对象
    private val placeService = ServiceCreator.create<PlaceService>()

    // 2.定义searchPlaces()函数，并调用PlaceService接口的searchPlaces()
    suspend fun searchPlaces(query: String) = placeService.searchPlaces(query).await()

    // 3.简化Retrofit回调
    private suspend fun <T> Call<T>.await(): T {
        return suspendCoroutine { continuation ->
            enqueue(object : Callback<T> {
                override fun onResponse(call: Call<T>, response: Response<T>) {
                    val body = response.body()
                    if (body != null) continuation.resume(body)
                    else continuation.resumeWithException(
                            RuntimeException("response body is null")
                    )
                }

                override fun onFailure(call: Call<T>, t: Throwable) {
                    continuation.resumeWithException(t)
                }

            })
        }
    }
}