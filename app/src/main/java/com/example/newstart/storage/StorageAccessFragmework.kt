package com.example.newstart.storage

import android.annotation.SuppressLint
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.ParcelFileDescriptor
import android.provider.OpenableColumns
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import java.io.FileDescriptor
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException


/**
 * Created by Ashutosh Ojha on 27,June,2022
 */
class StorageAccessFragmework : AppCompatActivity() {

    lateinit var imageView: ImageView
    private val WRITE_REQUEST_CODE = 43
    lateinit var createdFileUri: Uri


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_saf)
        val write = findViewById<Button>(R.id.btn1)
        val read = findViewById<Button>(R.id.btn)
        imageView = findViewById<ImageView>(R.id.imageView)

        write.setOnClickListener {
            //  writeToInternalStorage()
            //   writeToExternal()
            // performFileSearch()
            createFile()
        }
    }

    fun performFileSearch() {

        // ACTION_OPEN_DOCUMENT is the intent to choose a file via the system's file
        // browser.
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT)

        // Filter to only show results that can be "opened", such as a
        // file (as opposed to a list of contacts or timezones)
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        // Filter to show only images, using the image MIME data type.
        // If one wanted to search for ogg vorbis files, the type would be "audio/ogg".
        // To search for all documents available via installed storage providers,
        // it would be "*/*".
        intent.type = "image/*"
        startActivityForResult(intent, 100)
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int,
        resultData: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, resultData)

        // The ACTION_OPEN_DOCUMENT intent was sent with the request code
        // READ_REQUEST_CODE. If the request code seen here doesn't match, it's the
        // response to some other intent, and the code below shouldn't run at all.
        if (requestCode == 100 && resultCode == RESULT_OK) {
            // The document selected by the user won't be returned in the intent.
            // Instead, a URI to that document will be contained in the return intent
            // provided to this method as a parameter.
            // Pull that URI using resultData.getData().
            var uri: Uri? = null
            if (resultData != null) {
                uri = resultData.data
                Log.i("TAG", "Uri: " + uri.toString())
                //  showImage(uri)
                dumpImageMetaData(uri)
            }
        } else if (requestCode == WRITE_REQUEST_CODE && resultCode == RESULT_OK) {
            createdFileUri = resultData?.data!!

            alterDocument(createdFileUri)

        }
    }

    @SuppressLint("Range")
    fun dumpImageMetaData(uri: Uri?) {

        // The query, since it only applies to a single document, will only return
        // one row. There's no need to filter, sort, or select fields, since we want
        // all fields for one document.
        val cursor: Cursor = contentResolver
            .query(uri!!, null, null, null, null, null)!!
        try {
            // moveToFirst() returns false if the cursor has 0 rows.  Very handy for
            // "if there's anything to look at, look at it" conditionals.
            if (cursor != null && cursor.moveToFirst()) {

                // Note it's called "Display Name".  This is
                // provider-specific, and might not necessarily be the file name.
                val displayName: String = cursor.getString(
                    cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                )
                // Log.i("TAG, "$displayName")
                Log.d("Tag", "$displayName")
                val sizeIndex: Int = cursor.getColumnIndex(OpenableColumns.SIZE)
                // If the size is unknown, the value stored is null.  But since an
                // int can't be null in Java, the behavior is implementation-specific,
                // which is just a fancy term for "unpredictable".  So as
                // a rule, check if it's null before assigning to an int.  This will
                // happen often:  The storage API allows for remote files, whose
                // size might not be locally known.
                var size: String? = null
                size = if (!cursor.isNull(sizeIndex)) {
                    // Technically the column stores an int, but cursor.getString()
                    // will do the conversion automatically.
                    cursor.getString(sizeIndex)
                } else {
                    "Unknown"
                }
                // Log.i("AG, "Size: $size")

                getBitmapFromUri(uri)
            }
        } finally {
            cursor.close()
        }
    }

    private fun getBitmapFromUri(uri: Uri) {
        val parcelFileDescriptor = contentResolver.openFileDescriptor(uri, "r")
        val fileDescriptor: FileDescriptor = parcelFileDescriptor!!.fileDescriptor
        val image = BitmapFactory.decodeFileDescriptor(fileDescriptor)
        parcelFileDescriptor.close()

        imageView.setImageBitmap(image)
    }

    private fun createFile() {
        val mimeType = "*/*"
//        intent.setType("image/*");

        val fileName = "myNewFile.txt"
        val intent = Intent(Intent.ACTION_CREATE_DOCUMENT)

        // Filter to only show results that can be "opened", such as
        // a file (as opposed to a list of contacts or timezones).
        intent.addCategory(Intent.CATEGORY_OPENABLE)

        // Create a file with the requested MIME type.
        intent.type = mimeType
        intent.putExtra(Intent.EXTRA_TITLE, fileName)
        startActivityForResult(intent, WRITE_REQUEST_CODE)
    }

    private fun alterDocument(uri: Uri) {
        try {
            val pfd: ParcelFileDescriptor =
                contentResolver.openFileDescriptor(uri, "w")!!
            val fileOutputStream = FileOutputStream(pfd.fileDescriptor)
            fileOutputStream.write(
                """Overwritten by MyCloud at ${System.currentTimeMillis()}
    """.toByteArray()
            )
            // Let the document provider know you're done by closing the stream.
            fileOutputStream.close()
            pfd.close()
        } catch (e: FileNotFoundException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}