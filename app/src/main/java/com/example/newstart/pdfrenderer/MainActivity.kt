package com.example.newstart.pdfrenderer

import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.graphics.Bitmap
import android.graphics.pdf.PdfRenderer
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat.checkSelfPermission
import androidx.core.content.FileProvider
import com.example.newstart.BuildConfig
import com.example.newstart.R
import java.io.File
import java.io.FileDescriptor
import java.io.IOException
/**
 * Key string for saving the state of current page index.
 */
private const val CURRENT_PAGE_INDEX_KEY =
    "com.example.android.actionopendocument.state.CURRENT_PAGE_INDEX_KEY"

private const val TAG = "ActionOpenDocumentFragment"
private const val INITIAL_PAGE_INDEX = 0

class MainActivity : AppCompatActivity() {

    private val viewModel = DownloadViewModel()

    lateinit var buttonStartDownload:Button
//    lateinit var editTextFileUrl:EditText
    lateinit var progressBarStatus:ProgressBar
    private companion object {
        const val STORAGE_PERMISSION_REQUEST_CODE = 1234
    }

    //PDF Renderer

    private lateinit var pdfRenderer: PdfRenderer
    private lateinit var currentPage: PdfRenderer.Page
    private var currentPageNumber: Int = INITIAL_PAGE_INDEX

    private lateinit var pdfPageView: ImageView
    private lateinit var previousButton: Button
    private lateinit var nextButton: Button

    val pageCount get() = pdfRenderer.pageCount



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo)
        setupUI(savedInstanceState)
    }

    private fun setupUI(savedInstanceState: Bundle?) {
        progressBarStatus=findViewById(R.id.progressBarStatus)
//        editTextFileUrl=findViewById(R.id.editTextFileUrl)
        buttonStartDownload=findViewById(R.id.buttonStartDownload)
        buttonStartDownload.setOnClickListener {
            performStoragePermissionsCheck()
        }

        pdfPageView = findViewById(R.id.image)
        previousButton = findViewById<Button>(R.id.previous).apply {
            setOnClickListener {
                showPage(currentPage.index - 1)
            }
        }
        nextButton = findViewById<Button>(R.id.next).apply {
            setOnClickListener {
                showPage(currentPage.index + 1)
            }
        }

        // If there is a savedInstanceState (screen orientations, etc.), we restore the page index.
        currentPageNumber = savedInstanceState?.getInt(CURRENT_PAGE_INDEX_KEY, INITIAL_PAGE_INDEX)
            ?: INITIAL_PAGE_INDEX
    }

    private fun performStoragePermissionsCheck() {
        if (checkSelfPermission(this, WRITE_EXTERNAL_STORAGE) != PERMISSION_GRANTED) {
            requestPermissions(
                this,
                arrayOf(WRITE_EXTERNAL_STORAGE),
                STORAGE_PERMISSION_REQUEST_CODE
            )
        } else {
            onStorageUsePermissionGranted()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            STORAGE_PERMISSION_REQUEST_CODE -> {
                if ((grantResults.isNotEmpty() && grantResults[0] == PERMISSION_GRANTED)) {
                    onStorageUsePermissionGranted()
                } else {
                    toast(R.string.cannot_use_storage)
                }
            }
        }
    }

    private fun onStorageUsePermissionGranted() {
        buttonStartDownload.isEnabled = false
        val url = "https://maven.apache.org/archives/maven-1.x/maven.pdf"
        triggerFileDownload(url)
    }

    private fun triggerFileDownload(url: String) {
        viewModel.downloadFile(url).observe(this) {
            when (it) {
                is FileDownloadEvent.Progress -> {
                    buttonStartDownload.text = "Downloading... ${it.percentage}%"
                    progressBarStatus.updateProgress(it.percentage)
                }

                is FileDownloadEvent.Success -> {
                    toast(R.string.success)

                  //  callIntent(it.downloadedFilePath)
                    openPdfRenderer(it.downloadedFilePath)
                    buttonStartDownload.setText("R.string.start_download")
                    buttonStartDownload.isEnabled = true
                }

                is FileDownloadEvent.Failure -> {
                    toast(it.failure)
                    buttonStartDownload.setText("R.string.start_download")
                    buttonStartDownload.isEnabled = true
                }
            }
        }
    }

    private fun callIntent(downloadedFilePath: String) {
        val uri = FileProvider.getUriForFile(
            this@MainActivity,
            BuildConfig.APPLICATION_ID + ".provider",
            File(downloadedFilePath)
        )

        val i = Intent(Intent.ACTION_VIEW)
        i.setDataAndType(uri, "application/pdf")
        i.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_GRANT_READ_URI_PERMISSION
        startActivity(i)

    }

    private fun Context.toast(@StringRes message: Int, duration: Int = Toast.LENGTH_SHORT) =
        Toast.makeText(this, message, duration).show()


   fun openPdfRenderer(uri:String){
      // val documentUri = uri.toUri()
     val documentUri=  Uri.fromFile( File(uri))

       try {
           openRenderer(this, documentUri)
           showPage(currentPageNumber)
       } catch (ioException: IOException) {
           Log.d("TAG", "Exception opening document", ioException)
       }
   }

    /**
     * Sets up a [PdfRenderer] and related resources.
     */
    @Throws(IOException::class)
    private fun openRenderer(context: Context?, documentUri: Uri) {
        if (context == null) return

        /**
         * It may be tempting to use `use` here, but [PdfRenderer] expects to take ownership
         * of the [FileDescriptor], and, if we did use `use`, it would be auto-closed at the
         * end of the block, preventing us from rendering additional pages.
         */
        val fileDescriptor = context.contentResolver.openFileDescriptor(documentUri, "r") ?: return

        // This is the PdfRenderer we use to render the PDF.
        pdfRenderer = PdfRenderer(fileDescriptor)
        currentPage = pdfRenderer.openPage(currentPageNumber)
    }

    /**
     * Closes the [PdfRenderer] and related resources.
     *
     * @throws IOException When the PDF file cannot be closed.
     */
    @Throws(IOException::class)
    private fun closeRenderer() {
        currentPage.close()
        pdfRenderer.close()
    }

    /**
     * Shows the specified page of PDF to the screen.
     *
     * The way [PdfRenderer] works is that it allows for "opening" a page with the method
     * [PdfRenderer.openPage], which takes a (0 based) page number to open. This returns
     * a [PdfRenderer.Page] object, which represents the content of this page.
     *
     * There are two ways to render the content of a [PdfRenderer.Page].
     * [PdfRenderer.Page.RENDER_MODE_FOR_PRINT] and [PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY].
     * Since we're displaying the data on the screen of the device, we'll use the later.
     *
     * @param index The page index.
     */
    private fun showPage(index: Int) {
        if (index < 0 || index >= pdfRenderer.pageCount) return

        currentPage.close()
        currentPage = pdfRenderer.openPage(index)

        // Important: the destination bitmap must be ARGB (not RGB).
        val bitmap =
            Bitmap.createBitmap(currentPage.width, currentPage.height, Bitmap.Config.ARGB_8888)

        currentPage.render(bitmap, null, null, PdfRenderer.Page.RENDER_MODE_FOR_DISPLAY)
        pdfPageView.setImageBitmap(bitmap)

        val pageCount = pdfRenderer.pageCount
        previousButton.isEnabled = (0 != index)
        nextButton.isEnabled = (index + 1 < pageCount)
     //   activity?.title = getString(R.string.app_name_with_index, index + 1, pageCount)
    }
    override fun onSaveInstanceState(outState: Bundle) {
        outState.putInt(CURRENT_PAGE_INDEX_KEY, currentPage.index)
        super.onSaveInstanceState(outState)
    }
}
