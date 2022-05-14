package com.example.newstart.multiscreen.slidingpane

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 14,May,2022
 */
class DetailsFragment : Fragment() {
    val viewModel by activityViewModels<SlidingPaneVM>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_slding_details, container, false)
        return view.rootView
    }
}