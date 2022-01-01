package com.example.newstart.databinding

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.newstart.R


/**
 * Created by Ashutosh Ojha on 27,December,2021
 */
class MyDataActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMyDataBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_my_data)
        binding.data1 = "Hello"
        binding.data2 = 1234

        val viewModel: MyDataViewModel by viewModels()

            binding.user=viewModel.getUserObj()
        binding.includedLayout.newData=viewModel.getUserObj()


    }
}