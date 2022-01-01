package com.example.newstart.complex_recycler

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Ashutosh Ojha on 24,December,2021
 */
class ComplexRecyclerActivity : AppCompatActivity() {
    lateinit var list: ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(com.example.newstart.R.layout.activity_complex_recycler)

        list = ArrayList<String>()
        list.add("HEAD 1")
        list.add("Sub 11")
        list.add("Sub 12")
        list.add("Sub 13")
        list.add("Sub 14")
        list.add("HEAD 2")
        list.add("Sub 21")
        list.add("Sub 22")
        list.add("Sub 23")
        list.add("Sub 24")
        list.add("Sub 25")
        list.add("HEAD 3")
        list.add("Sub 31")
        list.add("Sub 32")
        list.add("Sub 33")
        list.add("Sub 34")
        list.add("Sub 35")
        list.add("HEAD 4")
        list.add("Sub 41")
        list.add("Sub 42")
        list.add("Sub 43")
        list.add("Sub 44")
        list.add("Sub 45")
        list.add("HEAD 5")
        list.add("Sub 51")
        list.add("Sub 52")
        list.add("Sub 53")
        list.add("Sub 54")
        list.add("Sub 55")

        val recyclerView = findViewById<View>(com.example.newstart.R.id.rv) as RecyclerView
        val adapter = FlowersAdapter(list)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        val recyclerViewItemDecorator =
            CustomRecyclerViewItemDecorator(
                this,
                60,
                true,
                getSectionCallback(list)
            )

        recyclerView.addItemDecoration(recyclerViewItemDecorator)
    }

   private fun getSectionCallback(array: ArrayList<String>): CustomRecyclerViewItemDecorator.SectionCallback {

        return object : CustomRecyclerViewItemDecorator.SectionCallback {
            override fun isSectionHeader(post: Int): Boolean {
                return post == 0 || array[post]!=array[post-1]
            }

            override fun getSectionHeaderName(post: Int): String {
                return array[post]
            }

        }
    }
}