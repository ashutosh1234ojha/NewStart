package com.example.newstart.storage

import android.Manifest
import android.content.ContentValues
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.media.MediaScannerConnection
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.os.Process
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream


/**
 * Created by Ashutosh Ojha on 29,June,2022
 */
class MediaStoreActivity :AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val write = findViewById<Button>(R.id.btn1)

        write.setOnClickListener {

        }
    }

    @Throws(IOException::class)
    private fun saveImageToStorage() {
        val imageOutStream: OutputStream? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val values = ContentValues()
            values.put(MediaStore.Images.Media.DISPLAY_NAME, "image_screenshot.jpg")
            values.put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
            values.put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
            val uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
            contentResolver.openOutputStream(uri!!)
        } else {
            val imagePath =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
                    .toString()
            val image = File(imagePath, "image_screenshotjpg")
            FileOutputStream(image)
        }
        try {
          //  bitmapObject.compress(Bitmap.CompressFormat.JPEG, 100, imageOutStream)
        } finally {
            imageOutStream!!.close()
        }
    }

    @Throws(IllegalStateException::class)
    private fun saveImageToDisk(bitmap: Bitmap) {
        val fileName = "..."

        if (Build.VERSION.SDK_INT < 29) {
            val canWriteStorage =
                Build.VERSION.SDK_INT < 23 || checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED
            check(canWriteStorage) { "no storage permission" }

            val directory = pictureFolder
            checkNotNull(directory) { "no storage permission" }

            if (!directory.exists()) {
                directory.mkdirs()
            }
            check(directory.exists()) { "folder could not be_created" }
            check(directory.canWrite()) { "dir $directory exists but is not writable" }

            val file = File(directory, fileName)
            FileOutputStream(file).use { outputStream ->
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            }
            addToGallery(file)
        } else {
            val contentValues = ContentValues()
            with(contentValues) {
                put(MediaStore.Images.Media.RELATIVE_PATH, Environment.DIRECTORY_PICTURES) // here you can add ' + "/" + subfolderName' to put the picture into a subfolder
                put(MediaStore.Images.Media.DISPLAY_NAME, fileName)
                put(MediaStore.Images.Media.MIME_TYPE, "image/png")
            }

            val imageUri: Uri? = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
            checkNotNull(imageUri) { "Could not get an uri for $contentValues" }

            val outputStream = contentResolver.openOutputStream(imageUri)
            checkNotNull(outputStream) { "Could not get an OutputStream for uri $imageUri" }
            outputStream.use { bitmap.compress(Bitmap.CompressFormat.PNG, 100, it) }
        }
    }


//    @SuppressLint("SdCardPath")
//    @Suppress("DEPRECATION") //
    private val pictureFolder: File? = run {
        arrayOf(
            Environment.getExternalStorageDirectory(),
            File("/storage/emulated/$userNum/"),
            File("/storage/emulated/legacy/"),
            File("/data/media/$userNum"),
            File("/sdcard/")
        )
            .asSequence()
            .flatMap { f -> listOf(f.canonicalPath, f.absolutePath) }
            .firstOrNull { File(it).canWrite() }
            ?.let { File(it, Environment.DIRECTORY_PICTURES) } // here you can add ' + "/" + subfolderName' to put the picture into a subfolder
    }

    private val userNum: Int = Process.myUid() / 100000

    fun addToGallery(path: String) {
        MediaScannerConnection.scanFile(this, arrayOf(path), null, null)
    }

    fun addToGallery(file: File) {
        addToGallery(file.absolutePath)
    }
}