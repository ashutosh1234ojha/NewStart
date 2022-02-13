package com.example.newstart.liveFlowdata

import android.app.Application
import android.os.CountDownTimer
import android.util.Log
import androidx.lifecycle.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Created by Ashutosh Ojha on 27,December,2021
 */
class LiveViewModel(application: Application) : AndroidViewModel(application) {
    private val countMutableLiveData = MutableLiveData<Int>()
    val countLiveData: LiveData<Int> = countMutableLiveData

    private val sourceOneMutableLiveData = MutableLiveData<String>()
    private val sourceTwoMutableLiveData = MutableLiveData<Int>()
    val sourceOneLiveData: LiveData<String> = sourceOneMutableLiveData
    val sourceTwoLiveData: LiveData<Int> = sourceTwoMutableLiveData

    val mediatorLiveData = MediatorLiveData<String>()


    var count = 0
    var countSourceOne = 0
    var countSourceTwo = 0

    private var _transformMutableLiveData = MutableLiveData<List<String>>()

    val _transformLiveData1: LiveData<List<String>> =
        Transformations.map(_transformMutableLiveData) { user ->
            // convertData(user)
            val resultList = mutableListOf<String>()
            for (u in user!!) {
                resultList.add(u + "Just")
            }
            resultList
        }


    private fun convertData(user: List<String>?): List<String> {
        val resultList = mutableListOf<String>()
        for (u in user!!) {
            resultList.add(u + "Just")
        }

        viewModelScope.launch {  }

        val user: LiveData<String> = liveData(Dispatchers.IO) {
            val data = "database.loadUser()" // loadUser is a suspend function.
            emit(data)
        }
        return resultList
    }

    init {
        mediatorLiveData.addSource(sourceOneLiveData, Observer {
            mediatorLiveData.value = it
        })

        mediatorLiveData.addSource(sourceTwoLiveData, Observer {
            mediatorLiveData.value = "$it"
        })

    }

    fun increaseCount() {

        val timer = object : CountDownTimer(20000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                count += 1
                countMutableLiveData.value = count
                Log.d("CountDownTimer", "${count}")
            }

            override fun onFinish() {
                Log.d("CountDownTimer", "Done ${count}")

            }
        }
        timer.start()


    }


    fun sourceOne() {
        val list = listOf<String>("One", "two", "three", "four", "five")
        sourceOneMutableLiveData.value = list.get(countSourceOne++)
    }

    fun sourceTwo() {
        val list = listOf<Int>(1, 2, 3, 4, 5)
        sourceTwoMutableLiveData.value = list.get(countSourceTwo++)
    }

    fun transformation() {
        _transformMutableLiveData.value =
            listOf("111One", "222two", "333three", "444four", "555five")
    }

}