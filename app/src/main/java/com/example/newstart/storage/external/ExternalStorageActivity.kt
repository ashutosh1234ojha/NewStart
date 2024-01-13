package com.example.newstart.storage.external

import android.Manifest
import android.app.RecoverableSecurityException
import android.content.ContentUris
import android.content.ContentValues
import android.content.pm.PackageManager
import android.database.ContentObserver
import android.graphics.Bitmap
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.newstart.databinding.ActivityStorageBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.io.IOException
import java.util.*

/**
 * Created by Ashutosh Ojha on 01,April,2022
 */
class ExternalStorageActivity : AppCompatActivity() {
    lateinit var rvadapter: ExternalStoragePhotoAdapter
    lateinit var rv: RecyclerView
    private var readPermissionGranted = false
    private var writePermissionGranted = false
    private lateinit var activityPermissionLauncher: ActivityResultLauncher<Array<String>>
    private lateinit var intentSenderLauncher: ActivityResultLauncher<IntentSenderRequest>
    private lateinit var contentObserver: ContentObserver
    private var deletedImageUri: Uri? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val bind = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(bind.root)

        rv = bind.rv
        rvadapter = ExternalStoragePhotoAdapter {
            //deleteFileFromInternalStorage(it.name)
            lifecycleScope.launch {
                deletePhotoFromExternalStorage(it.uri)
                deletedImageUri = it.uri
            }
        }
        setUpRv()
        intiContentObserver()
        initPermission()

        val takePhoto = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
            lifecycleScope.launch {
                val loadSuccess = savePhotoToExternalStorage(UUID.randomUUID().toString(), it!!)
                if (loadSuccess) {
                    loadFilesToRv()
                    Toast.makeText(
                        this@ExternalStorageActivity,
                        "Photo success",
                        Toast.LENGTH_SHORT
                    ).show()
                } else {
                    Toast.makeText(
                        this@ExternalStorageActivity,
                        "Photo Failure",
                        Toast.LENGTH_SHORT
                    ).show()

                }
            }
        }

        intentSenderLauncher =
            registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->

                if (result.resultCode == RESULT_OK) {
                    api29SpecialCase()
                    Toast.makeText(this, "Photo  delete  success", Toast.LENGTH_SHORT).show()

                } else {
                    Toast.makeText(this, "Photo  delete  failure", Toast.LENGTH_SHORT).show()

                }
            }

        bind.takePhoto.setOnClickListener {
            takePhoto.launch()
        }
    }

    private fun api29SpecialCase() {
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.Q) {
            lifecycleScope.launch {
                deletePhotoFromExternalStorage(deletedImageUri!!)
                return@launch
            }
        }
    }

    private fun setUpRv() {
        rv.apply {
            adapter = rvadapter
            layoutManager = StaggeredGridLayoutManager(4, RecyclerView.VERTICAL)
        }
    }

    private fun initPermission() {
        activityPermissionLauncher = registerForActivityResult(
            ActivityResultContracts
                .RequestMultiplePermissions()
        ) { permissions ->

            readPermissionGranted =
                permissions[Manifest.permission.READ_EXTERNAL_STORAGE] ?: readPermissionGranted
            writePermissionGranted =
                permissions[Manifest.permission.WRITE_EXTERNAL_STORAGE] ?: writePermissionGranted

            if (readPermissionGranted) {
                loadPhotosFromExternalStorageIntoRecyclerView()
            } else {
                Toast.makeText(this, "Can't read files without permission.", Toast.LENGTH_LONG)
                    .show()
            }
        }
        updateOrRequestPermission()
    }

    private fun loadFilesToRv() {

        lifecycleScope.launch {
            val listOfImage = loadPhotosFromExternalStorage()
            rvadapter.submitList(listOfImage)


        }

    }

    fun intiContentObserver() {

        contentObserver = object : ContentObserver(null) {
            override fun onChange(selfChange: Boolean) {
                Log.d("OnChangePhoto", "called" + selfChange)
                if (readPermissionGranted) {
                    loadPhotosFromExternalStorageIntoRecyclerView()
                }
            }
        }

        contentResolver.registerContentObserver(
            MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
            true,
            contentObserver
        )
    }

    private fun loadPhotosFromExternalStorageIntoRecyclerView() {
        lifecycleScope.launch {
            val photos = loadPhotosFromExternalStorage()
            rvadapter.submitList(photos)
        }
    }

    private fun updateOrRequestPermission() {
        val hasReadPermission = ContextCompat.checkSelfPermission(
            this,
            Manifest.permission.READ_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val hasWritePermission = ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED

        val minSdk29 = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

        readPermissionGranted = hasReadPermission
        writePermissionGranted = hasWritePermission || minSdk29

        val permissionToRequest = mutableListOf<String>()

        if (!readPermissionGranted) {
            permissionToRequest.add(Manifest.permission.READ_EXTERNAL_STORAGE)
        }

        if (!writePermissionGranted) {
            permissionToRequest.add(Manifest.permission.WRITE_EXTERNAL_STORAGE)
        }

        if (permissionToRequest.isNotEmpty()) {
            activityPermissionLauncher.launch(permissionToRequest.toTypedArray())
        } else {
            lifecycleScope.launch {
                loadPhotosFromExternalStorage()
            }
        }

    }

    private suspend fun deletePhotoFromExternalStorage(uri: Uri) {
        withContext(Dispatchers.IO) {
            try {

                contentResolver.delete(uri, null, null)
            } catch (e: SecurityException) {
                e.printStackTrace()
                val intentSender = when {
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.R -> { //API 30
                        MediaStore.createDeleteRequest(contentResolver, listOf(uri)).intentSender
                    }
                    Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q -> {//APi 29
                        val recoverableSecurityException = e as RecoverableSecurityException
                        recoverableSecurityException.userAction.actionIntent.intentSender
                    }
                    else -> null
                }
                intentSender?.let { sender ->
                    intentSenderLauncher.launch(IntentSenderRequest.Builder(sender).build())

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    private suspend fun savePhotoToExternalStorage(displayName: String, bmp: Bitmap): Boolean {

//        val imageCollection = sdk29AndUp {
//            MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
//        } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        return withContext(Dispatchers.IO) {
            val imageCollection =
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                    MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
                else MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val contentValue = ContentValues().apply {
                put(MediaStore.Images.Media.DISPLAY_NAME, "$displayName.jpg")
                put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
                put(MediaStore.Images.Media.WIDTH, bmp.width)
                put(MediaStore.Images.Media.HEIGHT, bmp.height)
            }

            try {
                contentResolver.insert(imageCollection, contentValue).also { uri ->
                    contentResolver.openOutputStream(uri!!).use { outputstream ->
                        if (!bmp.compress(Bitmap.CompressFormat.JPEG, 95, outputstream!!)) {
                            throw IOException("Couldn't save bitmap")
                        }
                    }
                } ?: throw IOException("Couldn't save bitmap")
                true
            } catch (e: Exception) {
                e.printStackTrace()
                false
            }
        }

    }

    private suspend fun loadPhotosFromExternalStorage(): List<SharedStoragePhoto> {
        return withContext(Dispatchers.IO) {
            val imageCollection = sdk29AndUp {
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
            } ?: MediaStore.Images.Media.EXTERNAL_CONTENT_URI

            val projection = arrayOf(
                MediaStore.Images.Media._ID,
                MediaStore.Images.Media.DISPLAY_NAME,
                MediaStore.Images.Media.WIDTH,
                MediaStore.Images.Media.HEIGHT
            )

            val photos = mutableListOf<SharedStoragePhoto>()
            contentResolver.query(
                imageCollection, projection, null, null,
                "${MediaStore.Images.Media.DISPLAY_NAME} ASC"
            )?.use { cursor ->
                val idColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media._ID)
                val displayNameColumn =
                    cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DISPLAY_NAME)
                val widthColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.WIDTH)
                val heightColumn = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.HEIGHT)

                while (cursor.moveToNext()) {
                    val id = cursor.getLong(idColumn)
                    val displayName = cursor.getString(displayNameColumn)
                    val width = cursor.getInt(widthColumn)
                    val height = cursor.getInt(heightColumn)
                    val contentUri =
                        ContentUris.withAppendedId(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, id)

                    photos.add(
                        SharedStoragePhoto(id, displayName, width, height, contentUri)
                    )
                }
                photos.toList()

            } ?: listOf()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        contentResolver.unregisterContentObserver(contentObserver)
    }
}

