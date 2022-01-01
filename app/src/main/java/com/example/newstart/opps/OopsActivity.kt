package com.example.newstart.opps

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 14,September,2021
 */
class OopsActivity :AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn=findViewById<Button>(R.id.btn)
        btn.setOnClickListener {

            val c=Aclass()
            c.fun1()



            val obj:Parent=Child()
          //  obj.draw()
            obj.draw(1,"obj")
            obj.draw("obj",1)
            obj.draw(1)

//          obj.draw(null) this is not allowed we have two method which can accept null value
            obj.a
            Log.d("OOPS","Oops Main ${obj.a}")
           // obj.draw(null)

            val obj1:ParentJava=ChildJava()
          //  obj1.printMy(null)





        }
    }
}