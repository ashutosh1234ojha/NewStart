package com.example.newstart

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import java.util.*


//ca-app-pub-2357253533591651~4681774364
// ca-app-pub-2357253533591651/6583202917

class MainActivity : AppCompatActivity() {
    lateinit var adRequest: AdRequest
    lateinit var ad_view: AdView
    var a: Unit = Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val btn = findViewById<Button>(R.id.btn)
        btn.setOnClickListener {

        }


        // MobileAds.initialize(this) {}
//        MobileAds.setRequestConfiguration(
//                RequestConfiguration.Builder()
//                        .setTestDeviceIds(listOf("ABCDEF012345"))
//                        .build()
//        )
//
//        ad_view = findViewById(R.id.ad_view) as AdView
////        ad_view.adUnitId = getString(R.string.banner_ad_unit_id)
//
//        val adRequest = AdRequest.Builder().build()
//        ad_view.loadAd(adRequest)
    }


    // Called when leaving the activity
    public override fun onPause() {
        //    ad_view.pause()
        super.onPause()
    }

    // Called when returning to the activity
    public override fun onResume() {
        super.onResume()
        //   ad_view.resume()
    }

    // Called before the activity is destroyed
    public override fun onDestroy() {
        //   ad_view.destroy()
        super.onDestroy()
    }

    fun a1() {
        val linkedList = LinkedList<Int>()
        linkedList.add(1)

        linkedList.reverse()


    }

    fun trycatch(): Int {
        try {
            return 1
        } catch (e: Exception) {
            return 2
        } finally {
            return 3
        }
    }


}


