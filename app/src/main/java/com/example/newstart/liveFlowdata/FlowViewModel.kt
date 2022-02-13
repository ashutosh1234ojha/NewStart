package com.example.newstart.liveFlowdata

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

/**
 * Created by Ashutosh Ojha on 27,December 2021
 */
class FlowViewModel(application: Application) : AndroidViewModel(application) {

    val flow = flow<Int> {
        val _count = 11
        var count = _count

        while (count > 0) {
            emit(count)
            delay(1000)
            count--
        }
    }

    val myStateFlow = MutableStateFlow<Int>(0)


    init {
        flowCollect()
        //  check()
    }

    fun flowCollect() {
        viewModelScope.launch {

            flow.collectLatest {
                delay(1500)
                Log.d("flowCollect", "$it")
            }
        }

        myStateFlow.value = 45

    }

    fun flatOperator() {
        val list = listOf<String>("one", "two")

        val flowOne = flow<String> {
            for (str in list)
                emit(str)
        }


        viewModelScope.launch {
            flowOne.flatMapMerge { value ->
                secondFlow(value)
            }.collect {
                Log.d("flatMapMerge", it)
            }

            flowOne.flatMapConcat { value ->
                secondFlow(value)
            }.collect {
                Log.d("flatMapConcat", it)
            }
        }
//
//        viewModelScope.launch {
//            flowOne.flatMapConcat { value ->
//                secondFlow(value)
//            }.collect {
//                Log.d("flatMapConcat", it)
//            }
//        }
    }

    fun secondFlow(value: String): Flow<String> {
        val list1 = listOf<String>("1", "2")

        val flowTwo = flow<String> {
            for (str in list1)
                emit(str + value)
        }

        return flowTwo
    }

    val flowCheck = kotlinx.coroutines.flow.flow {
        val _count = 11
        var count = _count

        while (count > 0) {
            emit(count)
            delay(1000)
            count--
        }
    }
    val _muta = MutableLiveData<Int>()
    var muta: LiveData<Int> = _muta
    fun check() {
        _muta.value = 34
    }


}