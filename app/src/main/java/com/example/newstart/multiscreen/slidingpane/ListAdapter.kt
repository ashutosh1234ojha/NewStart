package com.example.newstart.multiscreen.slidingpane

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 14,May,2022
 */
class ListAdapter(val list: ArrayList<MyData>) : RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    class MyViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val image = view.findViewById<ImageView>(R.id.image)
        val title = view.findViewById<TextView>(R.id.title)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(R.layout.item_sliding_list, parent)
        return MyViewHolder(layout.rootView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val context:Context=holder.image.context


        holder.image.setImageDrawable(holder.image.resources.getDrawable(list[position].drawable))
        holder.title.text = list[position].name
    }

    override fun getItemCount(): Int {
        return list.size
    }
}