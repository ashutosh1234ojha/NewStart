package com.example.newstart.databinding.databindingadapters

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 03,January,2022
 */

@BindingAdapter("imageName")
fun setImageFromAssets(view: ImageView, fileName: String) {
    try {
        val bitmap = BitmapFactory.decodeStream(view.context?.assets?.open(fileName))
        view.setImageBitmap(bitmap)
    } catch (e: Exception) {
        Log.d("BitmapMy",e.message.toString())
    }

}

@BindingAdapter("imageUrl")
fun setImageFromUrl(view: ImageView, fileName: String) {
    Glide
        .with(view)
        .load(fileName)
        .centerCrop()
        .placeholder(R.drawable.ic_launcher_background)
        .into(view);
}

@BindingAdapter(value = ["imageUrl","placeHolder"],requireAll = true)
fun setImageFromUrlAndPlaceholder(view: ImageView, fileName: String,placeHolder:Int) {

    R.drawable.border
    Glide
        .with(view)
        .load(fileName)
        .centerCrop()
        .placeholder(placeHolder)
        .into(view);
}