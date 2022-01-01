package com.example.newstart.higerorderfun

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R


class HigherOrderFunActivity : AppCompatActivity() {

    var a: Unit = Unit
    var str = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_higher_order)


        val data = ArrayList<String>()
        data.apply {
            add("one")
            add("two")
        }

        val high: (String) -> Unit = { a -> a }

        val rv = findViewById<RecyclerView>(R.id.rv)
//        rv.apply {
//            adapter = UserAdapter(data, object : ClickEvent {
//                override fun buttonClick(data: String) {
//
//                    Toast.makeText(this@HigherOrderFunActivity, data, Toast.LENGTH_SHORT).show()
//                }
//
//            })
//            layoutManager = LinearLayoutManager(this@HigherOrderFunActivity)
//        }

        rv.apply {
            adapter = UserAdapter1(data) {str1,str2->
                str=str1;
                showMe()
                Toast.makeText(
                    this@HigherOrderFunActivity,
                    str2,
                    Toast.LENGTH_SHORT
                ).show()
            }
            layoutManager = LinearLayoutManager(this@HigherOrderFunActivity)
        }

    }

    fun showMe(){
        Log.d("showMe",str)
    }
}