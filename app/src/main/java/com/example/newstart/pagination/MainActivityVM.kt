package com.example.newstart.pagination

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.*
import com.example.newstart.pagination.network.CharacterData
import com.example.newstart.pagination.network.RetroInstance
import com.example.newstart.pagination.network.RetroService
import com.example.newstart.pagination.room.CharDb
import kotlinx.coroutines.flow.Flow

/**
 * Created by Ashutosh Ojha on 10,April,2022
 */
class MainActivityVM(application: Application) : AndroidViewModel(application) {
    val db = CharDb.getInstance(getApplication())

    var retroService: RetroService =
        RetroInstance.getRetroInstance().create(RetroService::class.java)


    @ExperimentalPagingApi
    fun getListDataUsingMediator(): Flow<PagingData<CharacterData>> {
        return Pager(
            config = PagingConfig(pageSize = 10),
            remoteMediator = CharRemoteMediator(
                10,
                db,
                RetroInstance.getRetroInstance().create(RetroService::class.java)
            )
        ) {


         db.userDao().pagingSource()

        }.flow

    }

    fun getListData(): Flow<PagingData<CharacterData>> {
        return Pager(config = PagingConfig(pageSize = 20, maxSize = 200),
            pagingSourceFactory = { CharacterPagingSource(retroService) }).flow.cachedIn(
            viewModelScope
        )
    }
}