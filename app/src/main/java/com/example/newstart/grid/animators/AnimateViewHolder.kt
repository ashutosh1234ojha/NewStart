package com.example.newstart.grid.animators

import android.animation.Animator
import androidx.recyclerview.widget.RecyclerView

interface AnimateViewHolder {
  fun preAnimateAddImpl(holder: RecyclerView.ViewHolder)
  fun preAnimateRemoveImpl(holder: RecyclerView.ViewHolder)
  fun animateAddImpl(holder: RecyclerView.ViewHolder, listener: Animator.AnimatorListener)
  fun animateRemoveImpl(
    holder: RecyclerView.ViewHolder,
    listener: Animator.AnimatorListener
  )
}
