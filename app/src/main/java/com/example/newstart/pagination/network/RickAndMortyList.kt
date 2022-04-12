package com.example.newstart.pagination.network

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Ashutosh Ojha on 10,April,2022
 */
data class RickAndMortyList(val info: Info, val results: List<CharacterData>)

@Entity
data class CharacterData(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "name") val name: String?,
    @ColumnInfo(name = "species") val species: String?,
    @ColumnInfo(name = "image") val image: String?
)

data class Info(val count: Int?, val pages: String?, val next: String?, val prev: String?)