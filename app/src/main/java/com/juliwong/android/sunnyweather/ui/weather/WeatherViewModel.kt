package com.juliwong.android.sunnyweather.ui.weather

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.juliwong.android.sunnyweather.logic.Repository
import com.juliwong.android.sunnyweather.logic.model.place.Location

/**
 * @author JuliWong
 * @date 2021/3/17
 */
class WeatherViewModel : ViewModel() {

    // 界面相关的数据,保证手机屏幕发生旋转时不会丢失
    var locationLng = ""
    var locationLat = ""
    var placeName = ""

    private val locationLiveData = MutableLiveData<Location>()

    // 2.
    val weatherLiveData = Transformations.switchMap(locationLiveData) { location ->
        Repository.refreshWeather(location.lng, location.lat)
    }

    // 1.
    fun refreshWeather(lng: String, lat: String) {
        locationLiveData.value = Location(lng, lat)
    }
}