package com.example.newstart.designpattern.adapter

/**
 * Created by Ashutosh Ojha on 21,June,2022
 */
class School {
    fun assignment(){
        val assignment= WriteAssignment()
        assignment.setPen(AdapterPen())
        assignment.wirteMyAssignment("I am fast enough")
    }
}