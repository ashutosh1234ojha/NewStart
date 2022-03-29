package com.example.newstart.exception

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import java.io.File
import java.io.FileInputStream
import java.io.FileNotFoundException

/**
 * Created by Ashutosh Ojha on 16,February,2022
 */
class ExceptionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.btn)
        val btn1 = findViewById<Button>(R.id.btn1)

        btn.setOnClickListener {
            try {
                checkThrow(12)
            } catch (e: CustomException) {
                Log.d("NoError", e.message.toString())
            } catch (e: java.lang.Exception) {
                Log.d("NoError", e.message.toString())

            }
        }

        var a: Int = 9
        Log.d("HigherOrder", "before $a")

        btn1.setOnClickListener {
            //   checkThrows(13)
            //  checkedExceptionWithThrows()

            val c = high { x ->

                a = x
                return@high x

            }

            Log.d("HigherOrder", "after $a")
            Log.d("HigherOrder", "after c $c")

        }


    }


    private fun high(function: (Int) -> Int) {

        function(4)
    }

    private fun checkThrow(a: Int) {

//        try {
//            if(a>10){
//                throw  IllegalArgumentException()
//            }
//            Log.d("NoError","$a")
//        }catch (e:IllegalArgumentException){
//
//        }
        throw  CustomException("my custom exception")

    }

    private fun checkThrows(a: Int) {


        val file = File("not_existing_file.txt")
        val stream = FileInputStream(file)
//        try {
//            val stream = FileInputStream(file)
//        } catch (e: FileNotFoundException) {
//            e.printStackTrace()
//        }


        Log.d("NoError", "$a")
    }

    @Throws(FileNotFoundException::class, IllegalArgumentException::class)
    private fun checkedExceptionWithThrows() {
        val file = File("not_existing_file.txt")
        val stream = FileInputStream(file)
    }

    class CustomException(msg: String) : Exception(msg) {}
}