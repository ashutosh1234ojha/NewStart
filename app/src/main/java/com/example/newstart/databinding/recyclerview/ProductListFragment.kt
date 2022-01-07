package com.example.newstart.databinding.recyclerview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newstart.databinding.FragmentProductListBinding
import com.example.newstart.databinding.fragment.DataProvider

/**
 * Created by Ashutosh Ojha on 07,January,2022
 */

class ProductListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val binding =
            FragmentProductListBinding.inflate(inflater, container, false)

        binding.dataList = DataProvider.productList

        return binding.root
    }

}
