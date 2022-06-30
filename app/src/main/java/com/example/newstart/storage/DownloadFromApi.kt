package com.example.newstart.storage

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import com.example.newstart.BuildConfig
import com.example.newstart.R
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url
import java.io.File
import java.io.FileOutputStream


/**
 * Created by Ashutosh Ojha on 30,June,2022
 */
class DownloadFromApi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val write = findViewById<Button>(R.id.btn1)

        write.setOnClickListener {

            download()
        }
    }

    fun download() {
        val api = ServerAPI.retrofit.create(ServerAPI::class.java)
        api.downlload("archives/maven-1.x/maven.pdf")?.enqueue(object : Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>?, response: Response<ResponseBody?>) {
                try {
                    val mfile: File
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                        val path: File =
                            Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)
//                        val file = File(path, "file_name.pdf")
                        mfile = File(path, "file_name.pdf")
                        val fileOutputStream = FileOutputStream(mfile)
                        fileOutputStream.write(response.body()!!.bytes())
                    } else {
//                        val path: File = externalMediaDirs[0]!!
                        val path: File = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!
//                        val file = File(path, "file_name.pdf")
                        mfile = File(path, "file_name.pdf")

                        val fileOutputStream = FileOutputStream(mfile)
                        fileOutputStream.write(response.body()!!.bytes())
                    }

                    val uri = FileProvider.getUriForFile(
                        this@DownloadFromApi,
                        BuildConfig.APPLICATION_ID + ".provider",
                        mfile
                    )
                    val intent= Intent(Intent.ACTION_VIEW)
                    intent.setDataAndType(uri,"application/pdf")
                    intent.flags=Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_GRANT_READ_URI_PERMISSION
                    startActivity(intent)



                } catch (ex: Exception) {
                    ex.printStackTrace()
                }

            }

            override fun onFailure(call: Call<ResponseBody?>?, t: Throwable?) {}
        })
    }
}


interface ServerAPI {
    @GET
    fun downlload(@Url fileUrl: String?): Call<ResponseBody?>?

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://maven.apache.org/") // REMEMBER TO END with /
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}