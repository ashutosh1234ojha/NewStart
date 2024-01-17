package com.example.newstart.network

import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ApiCallActivity : AppCompatActivity() {

    lateinit var interfaceMy: APIInterface
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        val btn_save = findViewById<Button>(R.id.btn_save)

        interfaceMy = RetrofitBuilder.getClient()?.create(APIInterface::class.java)!!

        btn_save.setOnClickListener {

            val call: Call<String?> = interfaceMy?.doGetListResources()!!

            call.enqueue(object : Callback<String?> {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    Log.d("onResponse", response.body().toString())
                    Log.d("onResponse", response.body().toString())
                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    Log.d("onFailure", t.message.toString())

                }

            })

        };

    }
}
