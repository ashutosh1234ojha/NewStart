package com.example.newstart.navigatonview.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.newstart.R

class Fragment3 :Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment3, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val btn=view.findViewById<Button>(R.id.btnHome)
//        btn.setOnClickListener {
//            findNavController().navigate(R.id.action_myHomeFragment_to_mySecondFragment)
//        }
    }
}