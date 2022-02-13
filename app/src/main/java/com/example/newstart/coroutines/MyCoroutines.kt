package com.example.newstart.coroutines

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.newstart.Coding
import com.example.newstart.R
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.HashMap

/**
 * Created by Ashutosh Ojha on 12,September,2021
 */
class MyCoroutines : AppCompatActivity() {
    var count = 0
    val sDate:DateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS")


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
        val btn = findViewById<Button>(R.id.btn)
        val tvCM = findViewById<TextView>(R.id.tvCM)

        btn.setOnClickListener {

//            CoroutineScope(Dispatchers.IO).launch {
//                executeLong1()
//            }
//
//            CoroutineScope(Dispatchers.IO).launch {
//                executeLong2()
//            }
            count+=1
            tvCM.text="$count"


            Log.d("Timer", "Start of both "+sDate.format(Calendar.getInstance().time))

            CoroutineScope(Dispatchers.Main).launch {

               val first= CoroutineScope(Dispatchers.IO).async {

                    first()
                }

//            Log.d("Timer", "First Done &Second start"+sDate.format(Calendar.getInstance().time.toString()))

                val handler = CoroutineExceptionHandler { _, exception ->
                    println("Exception thrown in one of the children: $exception")
                }

                val parentJob = launch(handler) {}


                val second= CoroutineScope(Dispatchers.IO).async {

                    second()
                }

                Log.d("Timer", " both passed 1"+(sDate.format(Calendar.getInstance().time)+" "+first.await() +" "+second.await()))

                Log.d("Timer", " both passed 2 "+" "+first.await() +" "+second.await()+" "+(sDate.format(Calendar.getInstance().time)))
            }



        }

        btn1.setOnClickListener {


            val code=Coding();
            code.knight_()
//            runBlocking {
//                delay(10000)
//
//                Toast.makeText(this@MyCoroutines,"After 8000ms",Toast.LENGTH_SHORT).show()
//            }
//            CoroutineScope(Dispatchers.Main).launch {
//
//
//                delay(10000)
//
//                Toast.makeText(this@MyCoroutines,"After 8000ms",Toast.LENGTH_SHORT).show()
//
//            }
       //     Toast.makeText(this@MyCoroutines,"Outside coroutine",Toast.LENGTH_SHORT).show()

//
//            for(i in 1..399){
//
//            }
        }


//        btn1.setOnClickListener {
//            val obj = Coding()
//            obj.getUniqueNumber();
//        }

        val sum: (Int, Int) -> Int = { a, b -> a + b }
//        btn1.setOnClickListener {
//            //   myAdd(5, 8) { a, b -> a + b }
////            myAdd(5,4,::add)
//            flowTest()
//        }
        val map_1: Map<String, Int> = HashMap()

        val array = arrayOf(4)
        val list = mutableListOf<Int>()
        list.add(3)


    }

   suspend fun first(): IllegalAccessException {
       Log.d("first", Thread.currentThread().name)
       Log.d("Timer", "First Start"+sDate.format(Calendar.getInstance().time))
       delay(1000)
       Log.d("Timer", "First Done"+sDate.format(Calendar.getInstance().time))

       return IllegalAccessException("")
    }

    suspend fun second():String{
        Log.d("second", Thread.currentThread().name)
        Log.d("Timer", "Second Start"+sDate.format(Calendar.getInstance().time))

        delay(2000)
        Log.d("Timer", "Second  Done  "+sDate.format(Calendar.getInstance().time))

        return "second"
    }

   suspend fun executeLong1() {
       Log.d("executeLong", "executeLong1-Start")
       Log.d("executeLong", "executeLong1-Start"+Thread.currentThread().name)
       yield()
       Log.d("executeLong", "executeLong1-End")
       Log.d("executeLong", "executeLong1-End"+Thread.currentThread().name)
   }


    suspend fun executeLong2() {
        Log.d("executeLong", "executeLong2-Start")
        Log.d("executeLong", "executeLong2-Start"+Thread.currentThread().name)
        yield()
        Log.d("executeLong", "executeLong2-End")
        Log.d("executeLong", "executeLong2-End"+Thread.currentThread().name)
    }


    fun myAdd(a: Int, b: Int, add1: (Int, Int) -> Int) {
        val o = add1(a, b)
        Log.d("HighOrder", "$o")
        var hashMap: HashMap<String, Int> = HashMap<String, Int>()

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