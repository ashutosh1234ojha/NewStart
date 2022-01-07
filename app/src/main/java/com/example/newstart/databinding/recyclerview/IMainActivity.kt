package com.example.newstart.databinding.recyclerview

import com.example.newstart.databinding.fragment.Product

/**
 * Created by Ashutosh Ojha on 07,January,2022
 */
interface IMainActivity {

    fun onProductClick(product: Product)

    fun onProductImageClick(product: Product)
}