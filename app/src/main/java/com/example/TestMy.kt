package com.example

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 01,September,2021
 */
class TestMy :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_test_my)
        val rv=findViewById<RecyclerView>(R.id.rv)
        val tv=findViewById<TextView>(R.id.tv_steps1)
        tv.text="savedInstanceStatesavedInstanceStatesavedInstanceStatesavedInstanceStatesavedInstanceStatesavedInstanceStatesavedInstanceStatesavedInstanceState"
      val al=  ArrayList<String>()
        al.add("LinearLayoutManagerLinearLayoutManagerLinearLayoutManagerLinearLayoutManagerLinearLayoutManagerLinearLayoutManagerLinearLayoutManager");
        al.add("MyAdapterMyAdapterMyAdapterMyAdapterMyAdapterMyAdapterMyAdapterMyAdapterMyAdapterMyAdapter");
        rv.adapter=MyAdapter(al)
        rv.layoutManager=LinearLayoutManager(this)
    }
}

class MyAdapter(
    private var tempDataSet: ArrayList<String>
) :
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_not_online_fragment_item_gob, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = tempDataSet.size

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
//            if (i == tempDataSet.size - 1) {
//                viewHolder.bottomLine.visibility = View.GONE
//            } else {
//                viewHolder.bottomLine.visibility = View.VISIBLE
//            }
        val item = tempDataSet[i]
        /*if (item._isSelected) {
            viewHolder.checkUnCheckIV.setImageResource(R.drawable.ic_checkbox_select)
        } else {
            viewHolder.checkUnCheckIV.setImageResource(R.drawable.unchecked_checkbox)
        }*/
        viewHolder.stepsTV.text = item
//        viewHolder.stepIV.setImageResource(item.stepImage)
        //viewHolder.checkUnCheckIV.id = i
//        viewHolder.checkUnCheckIV.setOnClickListener {
//            listener.selPosition(it.id)
//            tempDataSet[i]._isSelected = !tempDataSet[i]._isSelected
//
//            notifyDataSetChanged()
//        }
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        val stepIV: ImageView = view.findViewById(R.id.iv_image)
        val stepsTV: TextView = view.findViewById(R.id.tv_steps)
        val checkUnCheckIV: ImageView = view.findViewById(R.id.radioButton_step_selection)
//        val checkUnCheckIV: ImageView = view.radioButton_step_selection
//        val bottomLine: View = view.view_line
    }
}