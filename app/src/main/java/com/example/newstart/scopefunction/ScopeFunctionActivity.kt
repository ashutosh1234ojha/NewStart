package com.example.newstart.scopefunction

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R

class ScopeFunctionActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {


//            letFun()
//            runFun()
//            withFun()
//            alsoFun()
            applyFun()
        }

    }

    private fun applyFun() {
        var gfg:Company ?=null
         gfg = Company()

        // initializing members of the class
        gfg.name = "GeeksforGeeks"
        gfg.objective = "A computer science portal for Geeks"
        gfg.founder = "Sandeep Jain"

        gfg?.apply {
            name="ashutosh"
        }
        println(gfg.name)

    }

    private fun alsoFun() {
    }

    private fun withFun() {
    }

    private fun runFun() {
    }

    private fun letFun() {
//        var a: Int? = null
//
//        a?.let {
//
//            print("not in")
//
//        }
//        a=90
//        a?.let {
//            print(it)
//
//        }


        val gfg = Company()

        // initializing members of the class
        gfg.name = "GeeksforGeeks"
        gfg.objective = "A computer science portal for Geeks"
        gfg.founder = "Sandeep Jain"

     val g=   gfg.let {
            it.name="ashutosh"
        }

        print(gfg.name)



    }


}