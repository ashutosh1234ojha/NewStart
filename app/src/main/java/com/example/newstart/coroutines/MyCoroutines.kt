package com.example.newstart.coroutines

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newstart.Coding
import com.example.newstart.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.util.stream.Collectors

/**
 * Created by Ashutosh Ojha on 12,September,2021
 */
class MyCoroutines : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        CoroutineScope(Dispatchers.IO).launch {

        }
//        LifecycleService.asy
//        viewLifecycleOwner.lifecycleScope.launch {
//            val params = TextViewCompat.getTextMetricsParams(textView)
//            val precomputedText = withContext(Dispatchers.Default) {
//                PrecomputedTextCompat.create(longTextContent, params)
//            }
//            TextViewCompat.setPrecomputedText(textView, precomputedText)
//        }

        val btn1 = findViewById<Button>(R.id.btn1)

        btn1.setOnClickListener {
            val obj=Coding()
            obj.getUniqueNumber();
        }

        val sum: (Int, Int) -> Int = { a, b -> a + b }
        btn1.setOnClickListener {
         //   myAdd(5, 8) { a, b -> a + b }
//            myAdd(5,4,::add)
            flowTest()
        }
        val map_1: Map<String, Int> = HashMap()

        val array= arrayOf(4)
        val list = mutableListOf<Int>()
        list.add(3)


    }


    fun myAdd(a: Int, b: Int, add1: (Int, Int) -> Int) {
        val o = add1(a, b)
        Log.d("HighOrder", "$o")
        var hashMap : HashMap<String, Int>
                = HashMap<String, Int> ()

        hashMap.containsKey("")

    }

    fun add(a: Int, b: Int): Int {
        return a + b
    }

    fun flowTest() {
        val flow = flow<String> {

            emit("Test")
            delay(1000)
        }

        lifecycleScope.launch {
            flow.collect {
                Log.d("HighOrder", it)
            }

        }
    }
}