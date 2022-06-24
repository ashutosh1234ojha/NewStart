package com.example.newstart.designpattern.adapter

/**
 * Created by Ashutosh Ojha on 21,June,2022
 */
class AdapterPen :Pen {
    val pilotPen=PilotPen()
    override fun write(str: String) {
        pilotPen.writePen(str)
    }
}