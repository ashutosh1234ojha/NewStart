package com.example.newstart.lifecycle

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 17,August,2021
 */
class FragmentA : Fragment() {
    val TAG = "LifeCycle Fragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView  A")

        return  inflater.inflate(R.layout.fragment_a, container, false);
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach  A")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate  A")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated  A")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart  A")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume  A")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause  A")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop  A")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView  A")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy  A")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach  A")

    }
}