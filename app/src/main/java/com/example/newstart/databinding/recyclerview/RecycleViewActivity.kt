package com.example.newstart.databinding.recyclerview

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.newstart.R
import com.example.newstart.databinding.ActivityRecyclerViewBinding
import com.example.newstart.databinding.fragment.DataProvider

/**
 * Created by Ashutosh Ojha on 03,January,2022
 */
class RecycleViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_recycler_view)
//        val rv_products=findViewById<RecyclerView>(R.id.rv_products)
//        rv_products.layoutManager = GridLayoutManager(this, 2)
//        rv_products.adapter = ProductsAdapter(this, DataProvider.productList.toList())
        val binding: ActivityRecyclerViewBinding? =
            DataBindingUtil.setContentView(this, R.layout.activity_recycler_view)

        binding?.dataList = DataProvider.productList.toList()
    }
}