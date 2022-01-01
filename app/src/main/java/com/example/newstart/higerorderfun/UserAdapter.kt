package com.example.newstart.higerorderfun

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


/**
 * Created by Ashutosh Ojha on 15,November,2021
 * https://burhanrashid52.com/2019/07/21/replace-the-callback-interface-with-higher-order-function-in-kotlin/
 */
class UserAdapter(val data: ArrayList<String>,val param: ClickEvent) :
    RecyclerView.Adapter<UserAdapter.UserViewHolder>() {

    class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val text = view.findViewById<TextView>(R.id.text1)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val listItem: View =
            layoutInflater.inflate(R.layout.activity_list_item, parent, false)
        return UserViewHolder(listItem)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.text.text = data[position]
        holder.text.setOnClickListener {
            param.buttonClick(data[position])
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }
}