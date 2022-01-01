package com.example.newstart.grid

import androidx.recyclerview.widget.RecyclerView
import android.content.Context
import android.view.View
import android.view.ViewGroup

import android.widget.TextView

import android.view.LayoutInflater

import android.animation.Animator

import android.animation.AnimatorListenerAdapter

import android.view.animation.AccelerateDecelerateInterpolator

import android.view.animation.DecelerateInterpolator

import android.animation.ObjectAnimator
import android.util.Log
import com.example.newstart.R


/**
 * Created by Ashutosh Ojha on 01,October,2021
 */
class GridAdapter(var dataList: ArrayList<String>) : RecyclerView.Adapter<GridAdapter.ViewHolder>() {

    var list=dataList
    var onCreateViewHolder=0;
    var onBindViewHolder=0;
    var getItemCount=0;
    var setList=HashSet<Int>()

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var tvCount: TextView = itemView.findViewById(R.id.tvCount)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val context: Context = parent.context
        val inflater = LayoutInflater.from(context)
        val contactView: View =
            inflater.inflate(R.layout.item_grid, parent, false)

        Log.d("onCreateViewHolder","${++onCreateViewHolder}")

        return ViewHolder(contactView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tvCount.text = dataList[holder.adapterPosition]
        Log.d("onBindViewHolder","${++onBindViewHolder}")
        setList.add(holder.hashCode())
        Log.d("holder","${setList.size}")

        holder.tvCount.setOnClickListener {


            val oa1 = ObjectAnimator.ofFloat(holder.tvCount, "scaleX", 1f, 0f)
            val oa2 = ObjectAnimator.ofFloat(holder.tvCount, "scaleX", 0f, 1f)
            oa1.interpolator = DecelerateInterpolator()
            oa2.interpolator = AccelerateDecelerateInterpolator()
            oa1.duration = 200
            oa2.duration = 200
            oa1.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    oa2.start()

                }
            })
            oa2.addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    super.onAnimationEnd(animation)
                    dataList.remove(holder.tvCount.text.toString())
                    notifyItemRemoved(holder.adapterPosition)
//                    notifyDataSetChanged()
                }
            })
            oa1.start()
        }
    }


    override fun getItemCount(): Int {
      //  Log.d("getItemCount","${++getItemCount}")

        return dataList.size
    }
}