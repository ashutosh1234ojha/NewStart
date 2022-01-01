package com.example.newstart.databinding.fragment

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.newstart.R
import com.example.newstart.databinding.FragmentMainBinding
import java.io.IOException

/**
 * Created by Ashutosh Ojha on 28,December,2021
 */
class MainFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding=FragmentMainBinding.inflate(inflater,container,false)

//        val rootView = inflater.inflate(R.layout.fragment_main, container, false)

//        val viewModel by viewModel.of(this)
//            .get(MainViewModel::class.java)
        val viewModel: MainViewModel by viewModels()


        setData(binding.root, viewModel.getProduct())
        binding.product=viewModel.getProduct()

        return binding.root

    }

    private fun setData(rootView: View, product: Product) {
//        rootView.tv_name.text = product.name
//        rootView.tv_price.text = "${product.price}$"
//        rootView.tv_description.text = product.description
//        rootView.tv_num_rating.text = "(${product.totalRatings})"
//
//        Log.d("MyTag", "inflateViewAndSetData: desc: ${product.description}")

//
//        rootView.iv_.visibility = if (product.salePrice > 0.0) View.VISIBLE else View.GONE
//        rootView.iv_product.setImageBitmap(getBitmapFromAssets(product.image))

    }

    private fun getBitmapFromAssets(fileName: String): Bitmap? {
        return try {
            BitmapFactory.decodeStream(context?.assets?.open(fileName))
        } catch (e: IOException) {
            e.printStackTrace()
            null
        }
    }


}