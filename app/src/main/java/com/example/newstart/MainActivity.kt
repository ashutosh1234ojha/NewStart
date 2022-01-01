package com.example.newstart

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.RequestConfiguration
import java.lang.StringBuilder
import java.util.*


//ca-app-pub-2357253533591651~4681774364
// ca-app-pub-2357253533591651/6583202917

class MainActivity : AppCompatActivity() {
  lateinit  var adRequest:AdRequest
  lateinit  var ad_view:AdView
  var a:Unit=Unit

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val btn=findViewById<Button>(R.id.btn)
        btn.setOnClickListener {
//            val singleLinkedList= SingleLinkedList()
//            val node= SingleLinkedList.Node(0)
//            singleLinkedList.head=node
//
//            val node1= SingleLinkedList.Node(1)
//            val node2= SingleLinkedList.Node(2)
//            val node3= SingleLinkedList.Node(3)
//            val node4= SingleLinkedList.Node(4)
//
//            node.next=node1
//            node1.next=node2
//            node2.next=node3
//            node3.next=node4
//            node4.next=null
//
//            singleLinkedList.display()
//
//          val pre=  singleLinkedList.reverse(node)
//            singleLinkedList.head=pre
//
//            singleLinkedList.display()


            val myClass=MyClass()

//                myClass.longestCommonSum(3)

           // myClass.doUnion();
            myClass.substrCount(5,"asasd");


         //   reverseAString();
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
      val linkedList=LinkedList<Int>()
        linkedList.add(1)

        linkedList.reverse()


    }

    fun trycatch():Int{
        try{
            return 1
        }catch (e:Exception){
            return  2
        }finally {
            return  3
        }
    }

}