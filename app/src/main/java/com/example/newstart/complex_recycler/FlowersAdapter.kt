package com.example.newstart.complex_recycler

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater

import android.widget.TextView
import com.example.newstart.R


/**
 * Created by Ashutosh Ojha on 24,December,2021
 */
class FlowersAdapter(val list: ArrayList<String>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    enum class VIEW_TYPE { HEAD, MAIN }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
            val layoutInflater = LayoutInflater.from(parent.context)
            val listItem: View =
                layoutInflater.inflate(R.layout.item_recycler_header, parent, false)
            MyViewHolderHead(listItem)

        } else {
            val layoutInflater = LayoutInflater.from(parent.context)
            val listItem: View = layoutInflater.inflate(R.layout.item_recycler, parent, false)
            MyViewHolder(listItem)

        }

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is MyViewHolderHead) {
            holder.text.text = list[position]

        } else if (holder is MyViewHolder) {
            holder.text.text = list[position]

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return if (list[position].contains("HEAD")) {
           // VIEW_TYPE.HEAD.ordinal
            0
        } else {
//            VIEW_TYPE.MAIN.ordinal
            1

        }
    }

    class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val text: TextView = view.findViewById<TextView>(R.id.header_text)

    }

    class MyViewHolderHead(view: View) : RecyclerView.ViewHolder(view) {
        val text = view.findViewById<TextView>(R.id.header_text1)

    }

}