package com.example.newstart.databinding.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.BR
import com.example.newstart.databinding.ProductItemBinding
import com.example.newstart.databinding.fragment.Product

/**
 * Created by Ashutosh Ojha on 03,January,2022
 */

class ProductsAdapter(private val context: Context, private val dataList: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.BindingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {

        val rootView: ViewDataBinding =
            ProductItemBinding.inflate(LayoutInflater.from(context), parent, false)

        return BindingViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val product = dataList[position]

        holder.itemBinding.setVariable(BR.productItem, product)
        holder.itemBinding.setVariable(BR.listener, context as IMainActivity)
        holder.itemBinding.executePendingBindings()

    }

    override fun getItemCount() = dataList.size

    class BindingViewHolder(val itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root)
}