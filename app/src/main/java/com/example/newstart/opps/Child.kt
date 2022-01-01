package com.example.newstart.opps

import android.util.Log
import java.lang.NullPointerException

/**
 * Created by Ashutosh Ojha on 14,September,2021
 */
open class Child : Parent(){


   override  var a = 2;
    override fun draw1() {
        Log.d("OOPS", "Child draw")
        throw NullPointerException()
    }

    override fun draw(a: Int, b: String) {
        Log.d("OOPS", "Child draw $b")
    }

    override fun draw(b: String, a: Int): Int {
        Log.d("OOPS", "Child draw $b")
        return 1
    }

   public  fun draw(a: Int) {
        Log.d("OOPS", "Child draw $a")
    }

    override fun draw(a: Int?) {
        Log.d("OOPS", "Child $a")
    }

    override fun draw(a: String?) {
        Log.d("OOPS", "Parent draw")
    }




    /**
     * Overloading the already overridden  method  is not allowed
     */
//     fun draw(){
//        Log.d("OOPS","Child draw")
//    }

}