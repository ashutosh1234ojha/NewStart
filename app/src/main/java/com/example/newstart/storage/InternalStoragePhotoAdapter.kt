package com.example.newstart.storage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintSet
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.databinding.ItemPhotoBinding

/**
 * Created by Ashutosh Ojha on 01,April,2022
 */

class InternalStoragePhotoAdapter(
    private val onPhotoClick: (InternalStorageImageData) -> Unit
) : ListAdapter<InternalStorageImageData, InternalStoragePhotoAdapter.PhotoViewHolder>(Companion) {

    inner class PhotoViewHolder(val binding: ItemPhotoBinding): RecyclerView.ViewHolder(binding.root)

    companion object : DiffUtil.ItemCallback<InternalStorageImageData>() {
        override fun areItemsTheSame(oldItem: InternalStorageImageData, newItem: InternalStorageImageData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: InternalStorageImageData, newItem: InternalStorageImageData): Boolean {
            return oldItem.name == newItem.name && oldItem.bmp.sameAs(newItem.bmp)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        return PhotoViewHolder(
            ItemPhotoBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = currentList[position]
        holder.binding.apply {
            ivPhoto.setImageBitmap(photo.bmp)

            val aspectRatio = photo.bmp.width.toFloat() / photo.bmp.height.toFloat()
            ConstraintSet().apply {
                clone(root)
                setDimensionRatio(ivPhoto.id, aspectRatio.toString())
                applyTo(root)
            }

            ivPhoto.setOnLongClickListener {
                onPhotoClick(photo)
                true
            }
        }
    }
}