package com.example.newstart.syncWithServer

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.widget.ImageView

import android.widget.TextView
import androidx.core.content.ContextCompat
import com.example.newstart.R
import com.example.newstart.complex_recycler.FlowersAdapter


/**
 * Created by Ashutosh Ojha on 24,December,2021
 */
class UserAdapter() :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var list: List<UserData> = ArrayList<UserData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View = layoutInflater.inflate(R.layout.item_synnc_list, parent, false)
        return MyViewHolderHead(listItem)

    }


    override fun onBindViewHolder(_holder: RecyclerView.ViewHolder, position: Int) {

        val holder = _holder as MyViewHolderHead
        holder.tvName.text = list[position].fName.plus(" ").plus(list[position].fName)
        holder.ivSync.setImageDrawable(
            ContextCompat.getDrawable(
                holder.tvName.context,
                if (list[position].isSynced == 1) R.drawable.baseline_check_circle_outline_24_filled else R.drawable.baseline_check_circle_outline_24
            )
        )


    }

    fun updateData(_list: List<UserData>) {
        this.list = _list
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list.size
    }


    class MyViewHolderHead(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.tvName)
        val ivSync = view.findViewById<ImageView>(R.id.ivSync)

    }

}