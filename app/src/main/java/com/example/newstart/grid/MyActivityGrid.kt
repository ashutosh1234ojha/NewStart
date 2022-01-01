package com.example.newstart.grid


import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R


/**
 * Created by Ashutosh Ojha on 01,October,2021
 */
class MyActivityGrid :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.newstart.R.layout.activity_my_grid)
        val dataList=ArrayList<String>()
        for(i in 0..200) {
            dataList.add(i.toString())
        }

        val btn=findViewById<Button>(R.id.btn)
        val recyclerView=findViewById<RecyclerView>(com.example.newstart.R.id.recyclerView)

        recyclerView.recycledViewPool.setMaxRecycledViews(0,50)
        recyclerView.setItemViewCacheSize(50)
        btn.setOnClickListener {
            recyclerView.apply {
                adapter=GridAdapter(dataList)
                layoutManager=LinearLayoutManager(this@MyActivityGrid)
//            layoutManager=GridLayoutManager(this@MyActivityGrid,3)
            }
        }




    }
}