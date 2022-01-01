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
class FragmentB : Fragment() {
    val TAG = "LifeCycle Fragment"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView  B")

        return  inflater.inflate(R.layout.fragment_b, container, false);
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach  B")

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate  B")

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated  B")
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart  B")

    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume  B")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause  B")

    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop  B")

    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView  B")

    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy  B")

    }

    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach  B")

    }
}