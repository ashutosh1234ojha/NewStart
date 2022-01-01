package com.example.newstart.databinding.fragment

import android.app.Application
import androidx.lifecycle.AndroidViewModel

/**
 * Created by Ashutosh Ojha on 28,December,2021
 */
class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var product = DataProvider.productList.get(0)

    fun getProduct(): Product = product

}