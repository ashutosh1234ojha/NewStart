package com.example.newstart.storage.external

import android.net.Uri

/**
 * Created by Ashutosh Ojha on 03,April,2022
 */
data class SharedStoragePhoto(
    val id: Long,
    val name: String,
    val width: Int,
    val height: Int,
    val uri: Uri
)