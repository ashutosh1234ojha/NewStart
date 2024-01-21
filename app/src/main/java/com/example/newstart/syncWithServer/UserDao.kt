package com.example.newstart.syncWithServer

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {

    @Query(" Select *  from userdata")
    fun getUserList(): Flow<List<UserData>>

    @Upsert
    fun addUser(myInfo: UserData):Long

    @Query(" Select *  from userdata where isSynced==0")
    fun getUnSyncedUserList():List<UserData>

    @Query(" Select *  from userdata where id=:rowId")
    fun getDataById( rowId:Long): UserData

}