package com.example.newstart.navigatonview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newstart.R

class MyThirdFragment :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.my_third_fragment, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn=view.findViewById<Button>(R.id.btnThird)
        val btn1=view.findViewById<Button>(R.id.btnThird1)
        btn.setOnClickListener {
            findNavController().navigate(R.id.action_myThirdFragment_to_myHomeFragment)
        }
        btn1.setOnClickListener {
            findNavController().navigate(R.id.action_myThirdFragment_to_mySecondFragment)
        }
    }
}