package com.example.newstart.room

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R

/**
 * Created by Ashutosh Ojha on 22,May,2022
 */
class RoomMigrationActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn1 = findViewById<Button>(R.id.btn1)
        val btn = findViewById<Button>(R.id.btn)
        val dao = MyInfoDatabase.getInstance(this).noteDao()
        btn1.setOnClickListener {
            val list = dao.getMyInfo()
            Log.d("ListMy", "${list.size}")
            if (list.isNotEmpty())
                Log.d("ListMy", "${list[list.size - 1]}")
        }

        btn.setOnClickListener {
//            dao.insetMyInfo(MyInfo(1, "10-12-2022", "hello"))
//            dao.insetMyInfo(MyInfo(2, "10-12-2022", "hello"))
//            dao.insetMyInfo(MyInfo(3, "10-12-2022", "hello"))
            dao.insetMyInfo(MyInfo(4, "10-12-2022", "hello","44"))


        }

    }
}