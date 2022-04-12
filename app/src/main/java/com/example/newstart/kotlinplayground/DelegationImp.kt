package com.example.newstart.kotlinplayground

import android.util.Log

/**
 * Created by Ashutosh Ojha on 09,April,2022
 */
open class DelegationImp : DelegationInterface {
    override fun getOne() {
        Log.d("DelegationImp", "GetOne")
    }

    override fun setOne() {
        Log.d("DelegationImp", "setOne")

    }
}