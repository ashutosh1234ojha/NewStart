package com.example.newstart.designpattern.adapter

/**
 * Created by Ashutosh Ojha on 21,June,2022
 */
class WriteAssignment  {
    lateinit var p:Pen

    fun getPen():Pen{
        return p
    }

    fun setPen(p:Pen){
        this.p=p
    }

    fun wirteMyAssignment(str:String){

        p.write(str)
    }
}