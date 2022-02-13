package com.example.newstart.liveFlowdata

import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.newstart.R
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

/**
 * Created by Ashutosh Ojha on 11,Febru ary 2022
 */
class FlowActivity : AppCompatActivity() {
    val flowViewModel: FlowViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data)

        val tvCount = findViewById<TextView>(R.id.tvCount)
        val btn = findViewById<TextView>(R.id.btn)
        val btn1 = findViewById<TextView>(R.id.btn1)
        val btn2 = findViewById<TextView>(R.id.btn2)

        btn.setOnClickListener {
            flowViewModel.flatOperator()
        }
        btn1.setOnClickListener {

            flowViewModel.check()
            flowViewModel.muta.observe(this, Observer {
                tvCount.text = "$it"

            })

            flowViewModel.muta.observe(this, Observer {
                tvCount.text = "$it"

            })
        }
        btn2.setOnClickListener {

            lifecycleScope.launch {

                flowViewModel.flowCheck.collect {
                    tvCount.text = "$it"

                }
            }

            lifecycleScope.launch {

                flowViewModel.flowCheck.collect {
                    tvCount.text = "$it"

                }
            }
        }




//        lifecycleScope.launch {
//            flowViewModel.flow.collect {
//
//                tvCount.text = "$it"
//            }
//        }


    }

}