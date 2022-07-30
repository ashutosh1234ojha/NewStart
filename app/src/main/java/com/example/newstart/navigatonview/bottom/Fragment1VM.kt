package com.example.newstart.navigatonview.bottom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.newstart.pagination.network.RetroInstance
import com.example.newstart.pagination.network.RetroService
import com.example.newstart.pagination.network.RickAndMortyList
import kotlinx.coroutines.launch

/**
 * Created by Ashutosh Ojha on 30,July,2022
 */
class Fragment1VM :ViewModel() {
    var retroService: RetroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
   private var liveData=MutableLiveData<RickAndMortyList>()
    var _liveData :LiveData<RickAndMortyList> = liveData
    var count=1

    fun makeApiCall(){
        viewModelScope.launch {
       val  tt=     retroService.getDataFromAPI(count++)
            liveData.value=tt

        }
    }
}