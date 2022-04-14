package com.example.newstart.pagination

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 14,April,2022
 */
class LoaderStateAdapter(val retry: () -> Unit) :
    LoadStateAdapter<LoaderStateAdapter.LoaderViewHolder>() {
    override fun onBindViewHolder(
        holder: LoaderViewHolder,
        loadState: LoadState
    ) {
        holder.bind(loadState)

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): LoaderViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_pagging_loader, parent, false)
        return LoaderViewHolder(view, retry)
    }


    class LoaderViewHolder(val view: View, val retry: () -> Unit) : RecyclerView.ViewHolder(view) {
        private val motionLayout: MotionLayout = view.findViewById(R.id.mlLoader)

        init {
            view.findViewById<Button>(R.id.btnRetry).setOnClickListener {
                retry()
            }
        }
        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Loading) {
                motionLayout.transitionToEnd()
            } else {
                motionLayout.transitionToStart()
            }

        }
    }
}