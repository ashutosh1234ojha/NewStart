package com.example.newstart.storage.external

import android.os.Build

/**
 * Created by Ashutosh Ojha on 03,April,2022
 */

inline fun <T> sdk29AndUp(sak29Up: () -> T): T? {
    return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        sak29Up()
    } else null

}