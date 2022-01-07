package com.example.newstart.databinding.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newstart.databinding.FragmentProductDetailBinding
import com.example.newstart.databinding.fragment.Product

/**
 * Created by Ashutosh Ojha on 07,January,2022
 */
class ProductDetailFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding = FragmentProductDetailBinding.inflate(inflater, container, false)

        val product = arguments?.getParcelable<Product>("product_key")

        binding.product = product!!
        binding.listener = context as IMainActivity

        return binding.root

    }
}