package com.juliwong.android.sunnyweather.ui.place

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.juliwong.android.sunnyweather.logic.Repository
import com.juliwong.android.sunnyweather.logic.dao.PlaceDao
import com.juliwong.android.sunnyweather.logic.model.place.Place

/**
 * @author JuliWong
 * @date 2021/3/16
 */
class PlaceViewModel : ViewModel() {
    // 缓存界面上显示的城市数据,保证手机屏幕发生旋转时不会丢失
    val placeList = ArrayList<Place>()

    private val searchLiveData = MutableLiveData<String>()

    // 2.
    val placeLivaData = Transformations.switchMap(searchLiveData) { query ->
        Repository.searchPlaces(query)
    }

    // 1.
    fun searchPlaces(query: String) {
        searchLiveData.value = query
    }

    /**
     * 保存Place的封装
     */
    fun savePlace(place: Place) = Repository.savePlace(place)
    fun getSavedPlace() = Repository.getSavedPlace()
    fun isPlaceSaved() = Repository.isPlaceSaved()
}