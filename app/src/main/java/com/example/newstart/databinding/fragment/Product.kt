package com.example.newstart.databinding.fragment

/**
 * Created by Ashutosh Ojha on 28,December,2021
 */
data class Product(
    val itemId: String,
    val name: String,
    val description: String,
    val price: Double,
    val salePrice: Double,
    val rating: Float,
    val totalRatings: Int,
    val image: String
)