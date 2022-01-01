package com.example.newstart.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface MyInfoDao {

    @Query(" Select *  from date_time")
    fun getMyInfo(): List<MyInfo>

    @Insert
    fun insetMyInfo(myInfo: MyInfo)
}