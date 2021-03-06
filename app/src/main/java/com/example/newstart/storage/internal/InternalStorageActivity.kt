package com.example.newstart.storage.internal

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newstart.databinding.ActivityStorageBinding
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*

/**
 * Created by Ashutosh Ojha on 01,April,2022
 */
class InternalStorageActivity : AppCompatActivity() {
    lateinit var rvadapter: InternalStoragePhotoAdapter
    lateinit var rv: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bind = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(bind.root)

        rv = bind.rv
        rvadapter = InternalStoragePhotoAdapter {
            deleteFileFromInternalStorage(it.name)
        }
        setUpRv()
        loadFilesToRv()

        val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            val loadSuccess = savePhotoToInternalStorage(UUID.randomUUID().toString(), it!!)

            if (loadSuccess) {
                loadFilesToRv()
                Toast.makeText(this, "Photo success", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Photo Failure", Toast.LENGTH_SHORT).show()

            }
        }

        bind.takePhoto.setOnClickListener {
            takePhoto.launch()
        }

    }

    private fun setUpRv() {
        rv.apply {
            adapter = rvadapter
            layoutManager = StaggeredGridLayoutManager(4, RecyclerView.VERTICAL)
        }
    }

    private fun loadFilesToRv() {

        lifecycleScope.launch {
            val listOfImage = loadPhotoFromInternalStorage()
            rvadapter.submitList(listOfImage)


        }

    }


    private fun savePhotoToInternalStorage(name: String, bmp: Bitmap): Boolean {

        val op = filesDir
        op.outputStream().write(bmp.rowBytes)
        return try {
            openFileOutput("$name.jpg", MODE_PRIVATE).use { stream ->
                if (!bmp.compress(Bitmap.CompressFormat.JPEG, 90, stream)) {
                    throw IOException("Could not save the bitmap")
                }
            }
            true
        } catch (io: IOException) {
            io.printStackTrace()
            false
        }

//        var stream:OutputStream ?=null
//        return try {
//             stream = openFileOutput("$name.jpg", MODE_PRIVATE) as OutputStream
//            val isCompressed = bmp.compress(Bitmap.CompressFormat.JPEG, 90, stream) as Boolean
//            if (!isCompressed) {
//                throw IOException("Could not save the bitmap")
//            }
//            true
//        } catch (e: Exception) {
//            e.printStackTrace()
//            false
//        }finally {
//            stream?.close()
//        }
    }

    private suspend fun loadPhotoFromInternalStorage(): List<InternalStorageImageData> {

        return withContext(IO) {

            val files = filesDir.listFiles()
            //  here  fileDir represent the  root directory of our internal storage
            // listFiles() returns all the  files  in that directory

            files?.filter { it.canRead() && it.isFile && it.name.endsWith((".jpg")) }?.map {
                val bytes = it.readBytes()
                val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                InternalStorageImageData(it.name, bmp)

            } ?: listOf()
        }
    }

    private fun deleteFileFromInternalStorage(fileName: String): Boolean {

        return try {
            deleteFile(fileName)
            loadFilesToRv()
            true

        } catch (e: Exception) {
            e.printStackTrace()
            false
        }
    }
}

