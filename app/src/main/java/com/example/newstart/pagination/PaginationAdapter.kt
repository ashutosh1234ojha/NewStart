package com.example.newstart.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.newstart.R
import com.example.newstart.pagination.network.CharacterData

/**
 * Created by Ashutosh Ojha on 10,April,2022
 */
class PaginationAdapter :
    PagingDataAdapter<CharacterData, PaginationAdapter.MyViewHolder>(MyDiffUtils()) {


    override fun onBindViewHolder(holder: PaginationAdapter.MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val inflater = LayoutInflater.from(parent.context).inflate(R.layout.rv_row, parent, false)
        return MyViewHolder(inflater)
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.imageView)
        val tvOne = view.findViewById<TextView>(R.id.tvOne)
        val tvTwo = view.findViewById<TextView>(R.id.tvTwo)
        fun bind(data: CharacterData?) {

            data?.let {
                tvOne.text = data.name
                tvTwo.text = data.species

                Glide.with(image).load(data.image).circleCrop().into(image)
            }

        }
    }

    class MyDiffUtils : DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name && oldItem.species == newItem.species

        }

    }
}