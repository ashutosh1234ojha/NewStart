package com.example.newstart.databinding.databindingadapters

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.databinding.fragment.Product
import com.example.newstart.databinding.recyclerview.ProductsAdapter

/**
 * Created by Ashutosh Ojha on 03,January,2022
 */

@BindingAdapter("bindList")
fun bindRecyclerViewList(view: RecyclerView, dataList: List<Product>) {

    if (dataList.isEmpty()) return
    if (view.layoutManager == null) {
        view.layoutManager = GridLayoutManager(view.context, 2)

    }
    if (view.adapter == null) {
        view.adapter = ProductsAdapter(view.context, dataList = dataList)

    }

}