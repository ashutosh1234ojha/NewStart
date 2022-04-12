package com.example.newstart.pagination

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.newstart.pagination.network.CharacterData
import com.example.newstart.pagination.network.RetroInstance
import com.example.newstart.pagination.network.RetroService
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ashutosh Ojha on 10,April,2022
 */
class MainActivityVM : ViewModel() {
    lateinit var retroService: RetroService

    init {
        retroService = RetroInstance.getRetroInstance().create(RetroService::class.java)
    }

    fun getListData(): Flow<PagingData<CharacterData>> {
//        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
//            pagingSourceFactory = { CharacterPagingSource(retroService) }).flow.cachedIn(
//            viewModelScope
//        )
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(retroService) }).flow.cachedIn(
            viewModelScope
        )
    }
}