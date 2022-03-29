package com.example.newstart.workmanager

import android.content.Context
import android.content.ContextWrapper
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.util.Log
import androidx.work.Data
import androidx.work.Worker
import androidx.work.WorkerParameters
import java.io.*
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.UUID.randomUUID

/**
 * Created by Ashutosh Ojha on 02,December,2021
 */
class DownloadWorkManager(context: Context, workerParams: WorkerParameters) : Worker(
    context,
    workerParams
) {
    override fun doWork(): Result {
        val urlString=inputData.getString("imageUrl")
        val url = stringToURL(urlString)
        Thread.sleep(10000)


        // IMPORTANT - Put internet permission on manifest file
        var connection: HttpURLConnection? = null


        try {
            // Initialize a new http url connection
            connection = url?.openConnection() as HttpURLConnection

            // Connect the http url connection
            connection.connect()

            // Get the input stream from http url connection
            val inputStream = connection.getInputStream()

            // Initialize a new BufferedInputStream from InputStream
            val bufferedInputStream = BufferedInputStream(inputStream)

            // Convert BufferedInputStream to Bitmap object

            // Return the downloaded bitmap
            val bmp: Bitmap? = BitmapFactory.decodeStream(bufferedInputStream)
            val uri: Uri? = bmp?.saveToInternalStorage(applicationContext)

            Log.d("download","success")
            // Return the success with output data
            return Result.success(createOutputData(uri))

        } catch (e: IOException) {
            e.printStackTrace()
            Log.d("download",e.toString())

        } finally {
            // Disconnect the http url connection
            connection?.disconnect()
        }
        Log.d("download","failed")
        return Result.failure(createOutputData(null))

    }
    // Method to create output data
    private fun createOutputData(uri: Uri?): Data {
        return Data.Builder()
            .putString("uriString", uri.toString())
            .build()
    }
    // Custom method to convert string to url
    fun stringToURL(urlString: String?): URL? {
        urlString?.let {
            try {
                return URL(urlString)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            }
        }

        return null
    }
}
// Extension function to save bitmap in internal storage
fun Bitmap.saveToInternalStorage(context: Context): Uri {
    // Get the context wrapper instance
    val wrapper = ContextWrapper(context)

    // Initializing a new file
    // The bellow line return a directory in internal storage
    var file = wrapper.getDir("images", Context.MODE_PRIVATE)


    // Create a file to save the image
    file = File(file, "${randomUUID()}.png")

    try {
        // Get the file output stream
        val stream: OutputStream = FileOutputStream(file)

        // Compress bitmap
        this.compress(Bitmap.CompressFormat.PNG, 100, stream)

        // Flush the stream
        stream.flush()

        // Close stream
        stream.close()
    } catch (e: IOException){ // Catch the exception
        e.printStackTrace()
    }

    // Return the saved image uri
    return Uri.parse(file.absolutePath)
}