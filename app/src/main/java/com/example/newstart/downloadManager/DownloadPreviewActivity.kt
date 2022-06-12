package com.example.newstart.downloadManager

import android.app.DownloadManager
import android.content.ContentValues
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.webkit.URLUtil
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.R
import java.io.File


/**
 * Created by Ashutosh Ojha on 09,June,2022
 */
class DownloadPreviewActivity :AppCompatActivity() {
    val imageURL =
//        "http://ichef.bbci.co.uk/onesport/cps/480/cpsprodpb/11136/production/_95324996_defoe_rex.jpg"
//        "https://ichef.bbci.co.uk/onesport/cps/480/cpsprodpb/11136/production/_95324996_defoe_rex.jpg"
//         "https://maven.apache.org/archives/maven-1.x/maven.pdf"
        "http://africau.edu/images/default/sample.pdf"
    lateinit var webView:WebView
    lateinit var tvCM: TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pdf)
        webView=findViewById<WebView>(R.id.webView)
        pdfView()


      //  download(imageURL)
//        startActivity(
//            Intent(
//                Intent.ACTION_VIEW,
//                Uri.parse(imageURL)
//            )
//        )


//        val btn1 = findViewById<TextView>(R.id.btn1)
//        tvCM = findViewById<TextView>(R.id.tvCM)
//        btn1.setOnClickListener {
//            download(imageURL)
//        }
    }

    private fun pdfView() {
        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptEnabled = true

        val settings=webView.settings
        settings.setSupportZoom(true)
        settings.javaScriptEnabled = true
        settings.loadWithOverviewMode = true
        settings.useWideViewPort = true
        settings.allowFileAccess = true
        settings.allowContentAccess = true
//        settings.allowFileAccessFromFileURLs = true
//        settings.allowUniversalAccessFromFileURLs = true
        settings.domStorageEnabled = true
        val url = imageURL
     //   webView.loadUrl(url)
      webView.loadUrl("https://docs.google.com/gview?embedded=true&url=$url")
    }

    private fun download(url: String) {
        val filename = URLUtil.guessFileName(url, null, null)
//        val file = File(getExternalFilesDir(Environment.DIRECTORY_DOWNLOADS).toString(), filename)
        val imageCollection =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)
            else MediaStore.Images.Media.EXTERNAL_CONTENT_URI

        val contentValue = ContentValues().apply {
            put(MediaStore.Images.Media.DISPLAY_NAME, "displayName.jpg")
            put(MediaStore.Images.Media.MIME_TYPE, "image/jpeg")
//            put(MediaStore.Images.Media.WIDTH, bmp.width)
//            put(MediaStore.Images.Media.HEIGHT, bmp.height)
        }
        val file = contentResolver.insert(imageCollection, contentValue).toString()

        val f = File(imageCollection.toString(), filename)
        var request: DownloadManager.Request? = null

        request = when {
            isDownloadManagerEqualOrAboveNougat() -> DownloadManager.Request(Uri.parse(url)).apply {
                setTitle(filename)
                setDescription(getString(R.string.str_desc_downloading))
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                    //     setDestinationUri(Uri.fromFile(f))
                    .setDestinationInExternalFilesDir(
                        this@DownloadPreviewActivity,
                        Environment.DIRECTORY_DOWNLOADS,
                        imageCollection.toString()
                    )
                setRequiresCharging(false)
                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
            }
            else -> DownloadManager.Request(Uri.parse(url)).apply {
                setTitle(filename)
                setDescription(getString(R.string.str_desc_downloading))
                setNotificationVisibility(DownloadManager.Request.VISIBILITY_HIDDEN)
                    //  setDestinationUri(Uri.fromFile(f))
                    .setDestinationInExternalFilesDir(
                        this@DownloadPreviewActivity,
                        Environment.DIRECTORY_DOWNLOADS,
                        imageCollection.toString()
                    )

                setAllowedOverMetered(true)
                setAllowedOverRoaming(true)
            }
        }
        val downloadManager =
            getSystemService(DOWNLOAD_SERVICE) as DownloadManager
        downloadManager.enqueue(request)
    }

    private fun isDownloadManagerEqualOrAboveNougat(): Boolean {
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
    }
}