package com.example.newstart.workmanager

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.newstart.MyClass
import com.example.newstart.databinding.ActivityWorkManagerBinding
import com.example.newstart.workmanager.room.CourseDatabase

/**
 * Created by Ashutosh Ojha on 24,November,2021
 */
class WorkManagerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWorkManagerBinding

    companion object {
        private const val GALLERY_REQUEST_CODE = 300
        private const val PERMISSIONS_REQUEST_CODE = 301

        private const val MAX_NUMBER_REQUEST_PERMISSIONS = 2

        private const val IMAGE_TYPE = "image/*"
        private const val IMAGE_CHOOSER_TITLE = "Select Picture"

        private val PERMISSIONS = arrayOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    }

    private var permissionRequestCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWorkManagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()

        requestPermissionsIfNecessary()

        binding.showLogs.setOnClickListener {
            val list = CourseDatabase.getInstance(this)?.Dao()?.getAllCourses()
            Log.d("DbList", "${list?.size}")
        }
        binding.downLoad.setOnClickListener {
            //createWorkManager()
            val v = MyClass()
            val hm = HashMap<Long, Long>()
            val re = v.fib(400, hm)

            Log.d("HashMap++++", "$re")


        }
    }

    private fun createWorkManager() {
        val data = Data.Builder()
        data.putString(
            "imageUrl",
            "https://www.freeimageslive.com/galleries/food/breakfast/pics/muffin1964.jpg"
        )

        val oneTimeWork =
            OneTimeWorkRequest.Builder(DownloadWorkManager::class.java).setInputData(data.build())
                .build()

        WorkManager.getInstance(this).enqueue(oneTimeWork)

        WorkManager.getInstance(this).getWorkInfoByIdLiveData(oneTimeWork.id)
            .observe(this, Observer { workInfo ->

                Toast.makeText(this, workInfo.state.name, Toast.LENGTH_SHORT).show()
                if (workInfo != null) {
                    when (workInfo.state) {
                        WorkInfo.State.ENQUEUED -> {
                            // Show the work state in text view
                            binding.downLoad.text = "enqueued."
                            binding.progressBar.visibility = View.VISIBLE

                        }
                        WorkInfo.State.BLOCKED -> {
                            binding.downLoad.text = "blocked."
                            binding.progressBar.visibility = View.VISIBLE

                        }
                        WorkInfo.State.RUNNING -> {
                            binding.downLoad.text = "running."
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        else->{

                        }
                    }
                }

                // When work finished
                if (workInfo != null && workInfo.state.isFinished) {
                    when (workInfo.state) {
                        WorkInfo.State.SUCCEEDED -> {
                            binding.downLoad.text = "Download successful."
                            binding.progressBar.visibility = View.GONE


                            // Get the output data
                            val successOutputData = workInfo.outputData
                            val uriText = successOutputData.getString("uriString")

                            // If uri is not null then show it
                            uriText?.apply {
                                // If download finished successfully then show the downloaded image in image view
                                binding.imageView.setImageURI(Uri.parse(uriText))
                                binding.downLoad.text = uriText
                            }
                        }
                        WorkInfo.State.FAILED -> {
                            binding.downLoad.text = "Failed to download."
                            binding.progressBar.visibility = View.GONE

                        }
                        WorkInfo.State.CANCELLED -> {
                            binding.downLoad.text = "Work request cancelled."
                            binding.progressBar.visibility = View.GONE

                        }
                        else->{}
                    }
                }

            })
    }

    private fun initUi() {
        binding.uploadGroup.visibility = View.GONE

        binding.pickPhotosButton.setOnClickListener { showPhotoPicker() }
    }

    private fun showPhotoPicker() {
        val intent = Intent().apply {
            type = IMAGE_TYPE
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true)
            action = Intent.ACTION_OPEN_DOCUMENT
        }

        startActivityForResult(
            Intent.createChooser(intent, IMAGE_CHOOSER_TITLE),
            GALLERY_REQUEST_CODE
        )
    }

    private fun requestPermissionsIfNecessary() {
        if (!hasRequiredPermissions()) {
            askForPermissions()
        }
    }

    private fun askForPermissions() {
        if (permissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
            permissionRequestCount += 1

            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSIONS_REQUEST_CODE)
        } else {
            binding.pickPhotosButton.isEnabled = false
        }
    }

    private fun hasRequiredPermissions(): Boolean {
        val permissionResults = PERMISSIONS.map { permission ->
            ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED
        }

        return permissionResults.all { isGranted -> isGranted }
    }

    override fun onRequestPermissionsResult(
        code: Int,
        permissions: Array<String>,
        result: IntArray
    ) {
        super.onRequestPermissionsResult(code, permissions, result)
        if (code == PERMISSIONS_REQUEST_CODE) {
            requestPermissionsIfNecessary()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (data != null
            && resultCode == Activity.RESULT_OK
            && requestCode == GALLERY_REQUEST_CODE
        ) {

            val applySepiaFilter = buildSepiaFilterRequests(data)

//            val conf=Configuration.Builder().setExecutor().build()
//            WorkManager.initialize(this,conf)
            val workManager = WorkManager.getInstance(this)
            workManager.beginWith(applySepiaFilter).enqueue()

        }
    }

    private fun buildSepiaFilterRequests(intent: Intent): List<OneTimeWorkRequest> {
        val filterRequests = mutableListOf<OneTimeWorkRequest>()

        intent.clipData?.run {
            for (i in 0 until itemCount) {
                val imageUri = getItemAt(i).uri

                val filterRequest = OneTimeWorkRequest.Builder(FilterWorker::class.java)
                    .setInputData(buildInputDataForFilter(imageUri, i))
                    .build()
                filterRequests.add(filterRequest)
            }
        }

        intent.data?.run {
            val filterWorkRequest = OneTimeWorkRequest.Builder(FilterWorker::class.java)
                .setInputData(buildInputDataForFilter(this, 0))
                .build()

            filterRequests.add(filterWorkRequest)
        }

        return filterRequests
    }

    private fun buildInputDataForFilter(imageUri: Uri?, index: Int): Data {
        val builder = Data.Builder()
        if (imageUri != null) {
            builder.putString(KEY_IMAGE_URI, imageUri.toString())
            builder.putInt(KEY_IMAGE_INDEX, index)
        }
        return builder.build()
    }

}