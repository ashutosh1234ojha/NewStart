package com.example.newstart.opps

import android.util.Log

/**
 * Created by Ashutosh Ojha on 25,October,2021
 */
class Aclass :Bclass(){
    val b=Bclass()

   fun fun1(){
       Log.d("Aclass","fun1")

      val tileaa:Tiles=Red("",2)
      val d= when(tileaa) {
          is Blue -> print("asd")
          is Red -> TODO()
      }

   }
    val gender=GENDER.FEMALE.functionOfGenderClass()

    val tile= Red("dot",3)
    val tile2= Red("plain",5)


}

enum class GENDER(a:Int) {
    MALE(1),
    FEMALE(3);
    fun functionOfGenderClass(){
        println(this)
    }
}

sealed class Tiles
class Red(val type:String,val points:Int):Tiles()
class Blue(val type:String,val points:Int):Tiles()