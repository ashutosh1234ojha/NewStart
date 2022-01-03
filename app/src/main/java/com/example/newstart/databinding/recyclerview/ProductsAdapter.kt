package com.example.newstart.databinding.recyclerview

import android.content.Context
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R
import com.example.newstart.databinding.fragment.Product

/**
 * Created by Ashutosh Ojha on 03,January,2022
 */

class ProductsAdapter(private val context: Context, private val dataList: List<Product>) :
    RecyclerView.Adapter<ProductsAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val rootView =
            LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)

        return MyViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val product = dataList[position]

        holder.tvName.text = product.name
        holder.tvPrice.text = "${product.price}$ "
        holder.tvRating.rating = product.rating
        holder.tvNumRating.text = "(${product.totalRatings})"

        try {
            val bitmap = BitmapFactory.decodeStream(context.assets.open(product.image))
            holder.ivProduct.setImageBitmap(bitmap)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        holder.ivSale.visibility = if (product.salePrice > 0) VISIBLE else GONE

    }

    override fun getItemCount() = dataList.size

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val tvName = itemView.findViewById<TextView>(R.id.tv_name)
        val tvPrice = itemView.findViewById<TextView>(R.id.tv_price)
        val tvRating = itemView.findViewById<RatingBar>(R.id.rating_bar)
        val tvNumRating = itemView.findViewById<TextView>(R.id.tv_num_rating)

        val ivProduct = itemView.findViewById<ImageView>(R.id.iv_product)
        val ivSale = itemView.findViewById<ImageView>(R.id.iv_product_sale)

    }

}