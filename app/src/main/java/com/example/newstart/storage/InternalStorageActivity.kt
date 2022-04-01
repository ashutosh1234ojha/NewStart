package com.example.newstart.storage

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
import com.example.newstart.databinding.ActivityInternalStorageBinding
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

        val bind = ActivityInternalStorageBinding.inflate(layoutInflater)
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

        bind.takePhoto.setOnClickListener{
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
        return try {

//            val stream = openFileOutput("$name.jpg", MODE_PRIVATE) as OutputStream
//            bmp.compress(Bitmap.CompressFormat.JPEG, 90, stream)
//            stream.close()
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

