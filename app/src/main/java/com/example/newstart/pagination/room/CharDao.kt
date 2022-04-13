package com.example.newstart.pagination.room

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.newstart.pagination.network.CharacterData

/**
 * Created by Ashutosh Ojha on 12,April,2022
 */
@Dao
interface CharDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(users: List<CharacterData>)

//    @Query("SELECT * FROM CharacterData WHERE uid LIKE :query")
//    fun pagingSource(query: Int): PagingSource<Int, CharacterData>

    @Query("SELECT * FROM CharacterData")
    fun pagingSource(): PagingSource<Int, CharacterData>

    @Query("DELETE FROM CharacterData")
    suspend fun clearAll()
}