package com.example

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.newstart.MySeal
import com.example.newstart.R
import com.example.newstart.TestCoroutines

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        MySeal.ab()

        val a=true;
        val b=false
        System.out.println("MYMYMY")
        System.out.println(b||b)

        val str="Colors Infinity,TV5Monde Asie Sep16,NEWSX Sep16,WION Aug16,Rev CNN,Rev2 BBC\n" +
                "World,Russia Today Sep16,Republic May17,Al Jazeera Apr19,France 24 Sep16,Australia Plus Sep16,STAR\n" +
                "Utsav Jun20,SAB HD Aug16,Zee TV HD,Star Bharat HD,AndTV HD,Colors HD,Star Plus HD,Cinema TV\n" +
                "India - Free Sep16,Enterr10 Movies Sep16,Star Utsav Movies Feb17,Star Gold Select Jul17,Rev UTV\n" +
                "Movies,UTV HD Nov18,Bflix Movies Oct16 Pack,B4U Movies Apr18,Zee Bollywood,Star Gold 2 Feb 20,B4U\n" +
                "Kadak Jul19,Rev MAX,Manoranjan TV Jul19,Dhinchaak Jun20,Zee Classic Jun19,Rev Zee Cinema,Rev\n" +
                "UTV Action,WOW CINEMA Sep16,CNBC Awaaz,India News Sep16,Rev NDTV India,Samay Jan20,Aaj Tak\n" +
                "Tez Oct17,Rev Aaj Tak,TV9 Bharatvarsh APR19,India TV Sep16,News 24 Sep16,News Nation Sep16,Zee\n" +
                "Hindustan May17,DD News HD Jan19,Zee Business,R Bharat Feb19,Zee News Sep16,News18 India\n" +
                "Apr18,ABP News Sep16,Zee UP UK Mar18,Marvel HQ,Rev Animal Planet,Food Food Sep16,SONY BBC\n" +
                "Earth Mar17,Epic,Rev 9XM,Mastiii Sep16,E24 Sep16,VH1 HD,Zoom Sep16,MTV Beats July17,B4U Music\n" +
                "Sep16,9X Jalwa Sep16,NEPAL1 Sep16,Naaptol - Free Sep16,Rang TV Sep16,PTC Punjabi Sep16,News18\n" +
                "Punjab Haryana Himachal,PTC News Sep16,MH1 News Sep16,MH One Sep16,Pitaara Dec17,Zee Punjab\n" +
                "Haryana Himachal,Jinvani TV Sep16,MHOne Shraddha Sep16,Sanskar TV Sep16,Vedic Aug17,Satsang\n" +
                "Sep16,Aastha Sep16,Sadhna Sep16,Ishwar Bhakti Sep16,Disha TV Sep16,Arihant Sep16,Lord Buddha TV\n" +
                "Apr17,Shubh TV Nov16,Star Sports Hindi 1 Sep16,Sony Ten 1 HD Sep16,Star Sports First"

        val btn=findViewById<Button>(R.id.btn)
        val tvCM=findViewById<TextView>(R.id.tvCM)
        var st=""
        btn.setOnClickListener {
//            val arr=   str.split(",")
//            var count=1;
//
//            for(va in arr){
//                Log.e(""+count++,va);
//                st=st+va+"\n"
//
//            }
//            tvCM.setText(st)
//            val intent= Intent(this,DashboardActivity::class.java)
//            startActivity(intent   )

            val test= TestCoroutines()
            test.test()

        }

    }



}