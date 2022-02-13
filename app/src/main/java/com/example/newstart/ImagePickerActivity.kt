package com.example.newstart

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.kbeanie.multipicker.api.CacheLocation
import com.kbeanie.multipicker.api.CameraImagePicker
import com.kbeanie.multipicker.api.ImagePicker
import com.kbeanie.multipicker.api.Picker
import com.kbeanie.multipicker.api.callbacks.ImagePickerCallback
import com.kbeanie.multipicker.api.entity.ChosenImage








/**
 * Created by Ashutosh Ojha on 10,February,2022
 */
class ImagePickerActivity : AppCompatActivity(), ImagePickerCallback {
    private var imagePicker: ImagePicker? = null
    private var cameraPicker: CameraImagePicker? = null
    private var pickerPath: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_picer)
        val image = findViewById<ImageView>(R.id.image)
        val pick = findViewById<Button>(R.id.pick)

        pick.setOnClickListener {

            Toast.makeText(this,"toat",Toast.LENGTH_SHORT).show()
            pickImageMultiple()
        }
    }

    fun pickImageMultiple() {
        imagePicker = ImagePicker(this)
        imagePicker?.setImagePickerCallback(this)
        imagePicker?.allowMultiple()
        imagePicker?.pickImage()
    }

    override fun onError(p0: String?) {

    }

    override fun onImagesChosen(p0: MutableList<ChosenImage>?) {
//        val adapter = MediaResultsAdapter(images, this)
//        lvResults.setAdapter(adapter)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Picker.PICK_IMAGE_DEVICE) {
                if (imagePicker == null) {
                    imagePicker = ImagePicker(this)
                    imagePicker!!.setImagePickerCallback(this)
                }
                imagePicker!!.submit(data)
            } else if (requestCode == Picker.PICK_IMAGE_CAMERA) {
                if (cameraPicker == null) {
                    cameraPicker = CameraImagePicker(this)
                    cameraPicker?.setImagePickerCallback(this)
                    cameraPicker?.reinitialize(pickerPath)
                }
                cameraPicker?.submit(data)
            }

        }
    }

    fun takePicture() {
        cameraPicker = CameraImagePicker(this)
        cameraPicker?.setDebugglable(true)
        cameraPicker?.setCacheLocation(CacheLocation.EXTERNAL_STORAGE_APP_DIR)
        cameraPicker?.setImagePickerCallback(this)
        cameraPicker?.shouldGenerateMetadata(true)
        cameraPicker?.shouldGenerateThumbnails(true)
        pickerPath = cameraPicker?.pickImage()
    }
}