package com.example.newstart.opps

import android.util.Log

/**
 * Created by Ashutosh Ojha on 14,September,2021
 */
open class Parent {
   open var a=10;
  open  fun draw1() {
        Log.d("OOPS","Parent draw")
      throw Exception()
    }

    open  fun draw( a:Int, b:String){
        Log.d("OOPS","Parent draw")
    }
    open  fun draw(bc:String, a:Int) :Int{
        Log.d("OOPS","Parent draw")
        return 1
    }

    open  fun draw(a:Float) {
        Log.d("OOPS","Parent draw float")
    }
    open  fun draw( a:Int ?){
        Log.d("OOPS","Parent draw")
    }
    open  fun draw( a:String ?){
        Log.d("OOPS","Parent draw")
    }

  open  fun draw( a:Int ,b:Int){
        Log.d("OOPS","Parent draw")
    }

    open fun abc(){}

}