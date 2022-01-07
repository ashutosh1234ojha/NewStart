package com.example.newstart.databinding.databindingadapters

import android.graphics.BitmapFactory
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.newstart.R
import java.text.NumberFormat
import java.util.*

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

@BindingAdapter("ratingText")
fun setTotalRatingText(view: TextView, rating: Int) {
    view.text = "($rating)"
}

@BindingAdapter("android:text")
fun setTextViewText(view: TextView, text: Double) {
    view.text = "$text"
}


@BindingAdapter("priceText")
fun setPriceText(view: TextView, price: Double) {
    val formatter = NumberFormat.getCurrencyInstance(Locale.US)

    view.text = "Rs. $price / each"
}
