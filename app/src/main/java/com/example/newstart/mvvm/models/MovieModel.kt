package com.example.newstart.mvvm.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

/**
 * Created by Ashutosh Ojha on 27,January,2022
 */
@Parcelize
data class MovieModel(
    val title: String,
    val poster_path: String,
    val release_date: String,
    val id: Int,
    val vote_average: Float,
    val overview: String
) : Parcelable
