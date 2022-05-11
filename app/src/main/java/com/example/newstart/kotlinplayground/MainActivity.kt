package com.example.newstart.kotlinplayground

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import kotlinx.coroutines.Job
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by Ashutosh Ojha on 09,April,2022
 */
class MainActivity : AppCompatActivity(), DelegationInterface by DelegationImp() {
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn1 = findViewById<Button>(R.id.btn1)
        val btn = findViewById<Button>(R.id.btn)
        imageView = findViewById<ImageView>(R.id.imageView)
        var job: Job? = null
        btn1.setOnClickListener {

            download()


//
//             job =  lifecycleScope.launch(Dispatchers.IO) {
//                for(i  in 1..10000){
//
//
////                    if(job!!.isActive){
//                        Log.d("Taggg","i $i"+" isActive")
//                        delay(1000)
////                    }else{
////                    //    Log.d("Taggg","i $i"+" Cancelled "+job!!.isCancelled)
////
////                    }
//
//                    Log.d("Taggg","i $i"+" Cancelled "+job!!.isCancelled)
//
//                }
//            }
        }



        btn.setOnClickListener {
            job?.cancel()
        }


    }

    private fun download() {
        var bitmapImage:Bitmap?=null
        val thread = Thread() {
            kotlin.run {
                val imageUrl =
                    URL("https://developer.android.com/images/kotlin/cards/kotlin-bootcamp.png")

                val httpConnection = imageUrl.openConnection() as HttpURLConnection
                httpConnection.doInput = true // for receiving data
                httpConnection.connect()

                val inputStream = httpConnection.inputStream
                 bitmapImage = BitmapFactory.decodeStream(inputStream)

                Log.d("bitmapImage", bitmapImage.toString())

//                runOnUiThread {
//                    imageView.setImageBitmap(bitmapImage)
//                }
            }
        }
        thread.start()

        thread.stop()

       Handler().postDelayed(object :Runnable{
            override fun run() {
                imageView.setImageBitmap(bitmapImage)
            }

        },5000)
    }


    fun crewOfSloths(list: List<Mammal>) {

//        list =Sloth("")
        list.forEach {
            Log.d("crewOfSloths", "Eat")
        }

    }

    private fun generics() {

        val listOfSloth = listOf(
            Sloth(""),
            Sloth("")
        )
        crewOfSloths(listOfSloth)

        val listOfPanda = listOf(
            Panda(""),
            Panda("")
        )

        crewOfSloths(listOfPanda)
    }
}