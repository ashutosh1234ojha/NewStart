package com.example.newstart.cache.feature.restaurant

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil.ItemCallback
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newstart.cache.data.Restaurant
import com.example.newstart.databinding.RestaurantItemBinding

/**
 * Created by Ashutosh Ojha on 30,January,2022
 */
class RestaurantAdapter :
    ListAdapter<Restaurant, RestaurantAdapter.RestaurantViewHolder>(RestaurantComparator()) {


    class RestaurantViewHolder(private val binding: RestaurantItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {

            binding.apply {
                Glide.with(itemView).load(restaurant.logo).into(imageViewLogo)
                tvRestaurantName.text = restaurant.name
                tvAddress.text = restaurant.address
                tvType.text = restaurant.type

            }

        }

    }

    class RestaurantComparator : ItemCallback<Restaurant>() {
        override fun areItemsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Restaurant, newItem: Restaurant): Boolean {
            return oldItem == newItem
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val binding =
            RestaurantItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RestaurantViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RestaurantViewHolder, position: Int) {

        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}