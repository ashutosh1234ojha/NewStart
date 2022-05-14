package com.example.newstart.multiscreen.slidingpane

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 14,May,2022
 */
class SlidingPaneVM : ViewModel() {

    private val mutableList = MutableLiveData<List<MyData>>()
    val liveList: LiveData<List<MyData>> = mutableList
    private val currentData = MutableLiveData<MyData>()
    val currentDataLive: LiveData<MyData> = currentData

    init {
        val list = ArrayList<MyData>()
        list.add(MyData(R.drawable.kermit1, "kermit1", "kermit1 Desc"))
        list.add(MyData(R.drawable.kermit2, "kermit1", "kermit1 Desc"))
        list.add(MyData(R.drawable.kermit3, "kermit1", "kermit1 Desc"))
        list.add(MyData(R.drawable.kermit4, "kermit1", "kermit1 Desc"))
        mutableList.value = list

        currentData.value = list[0]
    }

    fun setCurrent(data: MyData) {
        currentData.value = data
    }
}