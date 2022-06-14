package com.example.newstart.storage

import android.content.ContentValues
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
const val PICK_PDF_FILE = 2

/**
 * Created by Ashutosh Ojha on 13,June,2022
 */
class TestStorageActivity :AppCompatActivity() {
 lateinit var btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)

        btn=findViewById(R.id.btn)
        btn.setOnClickListener {
            val internal= Environment.DIRECTORY_DOWNLOADS
            val pic= Environment.DIRECTORY_PICTURES
         val  ex=   getExternalFilesDir("")

       //   addVideo()
            openFile("")

        }

    }

    private fun addVideo() {
        // Add a specific media item.
        val resolver = applicationContext.contentResolver

// Find all audio files on the primary external storage device.
        val audioCollection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                MediaStore.Images.Media.getContentUri(
                    MediaStore.VOLUME_EXTERNAL_PRIMARY
                )
            } else {
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            }

// Publish a new song.
        val newSongDetails = ContentValues().apply {
            put(MediaStore.Audio.Media.DISPLAY_NAME, "My Song.mp3")
        }

// Keeps a handle to the new song's URI in case we need to modify it
// later.
        val myFavoriteSongUri = resolver
            .insert(audioCollection, newSongDetails)
    }


    fun openFile(pickerInitialUri: String) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "application/pdf"

            // Optionally, specify a URI for the file that should appear in the
            // system file picker when it loads.
           // putExtra(DocumentsContract.EXTRA_INITIAL_URI, pickerInitialUri)
        }

        startActivityForResult(intent, PICK_PDF_FILE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==PICK_PDF_FILE){
        }
    }
}