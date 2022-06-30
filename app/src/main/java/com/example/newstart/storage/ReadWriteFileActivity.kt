package com.example.newstart.storage

import android.content.Context
import android.os.Bundle
import android.os.Environment
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import java.io.*


/**
 * Created by Ashutosh Ojha on 25,June,2022
 */
class ReadWriteFileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val write = findViewById<Button>(R.id.btn1)
        val read = findViewById<Button>(R.id.btn)

        write.setOnClickListener {
            //  writeToInternalStorage()
            writeToExternal()
        }

        read.setOnClickListener {
            readFromInternalStorage()
        }


    }

    fun writeToInternalProcess() {
        //            val fileName = "testt.txt"
//            val outputString: OutputStream
        val input = "my first string file"
//
//            outputString = openFileOutput(fileName, MODE_PRIVATE)
//            outputString.write(input.toByteArray())

        try {
            val outputStreamWriter = OutputStreamWriter(
                openFileOutput(
                    "todolist.txt",
                    Context.MODE_APPEND
                )
            )
            outputStreamWriter.write(input)
            outputStreamWriter.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    @Throws(IOException::class)
    private fun readToInternalProcess(): String? {
        var result = ""
        val inputStream: InputStream? = openFileInput("todolist.txt")
        if (inputStream != null) {
            val inputStreamReader = InputStreamReader(inputStream)
            val bufferedReader = BufferedReader(inputStreamReader)
            var temp: String? = ""
            val stringBuilder = StringBuilder()
            while (bufferedReader.readLine().also { temp = it } != null) {
                stringBuilder.append(temp)
                stringBuilder.append("\n")
            }
            inputStream.close()
            result = stringBuilder.toString()
        }
        return result
    }

    fun writeToInternalStorage() {
        val path: File = filesDir
        val file = File(path, "checkMyFile.txt")


        val stream = FileOutputStream(file)
        try {
            stream.write("text-to-write".toByteArray())
        } finally {
            stream.close()
        }
        Toast.makeText(this, "written", Toast.LENGTH_SHORT).show()


    }

    fun readFromInternalStorage() {
        val path: File = filesDir
        val file = File(path, "checkMyFile.txt")
        val length = file.length().toInt()

        val bytes = ByteArray(length)

        val `in` = FileInputStream(file)
        `in`.use { `in` ->
            `in`.read(bytes)
        }

        val contents = String(bytes)

        Toast.makeText(this, contents, Toast.LENGTH_SHORT).show()

    }

    fun writeToExternal() {
//        val path: File = getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS)!!

        //It will create file inside document folder
        val path: File = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS)

//      val path=  Environment.getExternalStorageDirectory()
//
        val file = File(path, "externalFileNext.txt")


        val stream = FileOutputStream(file)
        try {
            stream.write("text-to-write".toByteArray())
        } finally {
            stream.close()
        }
        Toast.makeText(this, "written", Toast.LENGTH_SHORT).show()

    }




}