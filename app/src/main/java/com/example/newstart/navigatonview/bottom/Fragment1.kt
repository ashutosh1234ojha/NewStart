package com.example.newstart.navigatonview.bottom

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.newstart.R

class Fragment1 :Fragment() {

    val viewModel: Fragment1VM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    lateinit var text:TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment1, container, false);
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btn=view.findViewById<Button>(R.id.btnRouterDetails)
         text=view.findViewById<TextView>(R.id.tvHome)

        init()
        btn.setOnClickListener {
           findNavController().navigate(R.id.action_fragment1_to_detailsFragment)
        }
    }

    fun  init(){
        lifecycleScope.launchWhenCreated {
            viewModel.makeApiCall()
        }
        viewModel._liveData.observe(requireActivity()) {
            text.text= it.results[0].species
        }
    }
}