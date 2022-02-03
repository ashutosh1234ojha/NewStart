package com.example.newstart;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

/**
 * Created by Ashutosh Ojha on 03,January,2022
 */
public class ProgramActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.collapsing_toolnar);
        Coding coding = new Coding();
        int[] arr = {2,3};
        ArrayList<ArrayList<Integer>> out   =
                coding.findStairs(4, arr);

        if(out==null){
            Log.d("ArrayList", "out?.toString()");

        }else {
            Log.d("ArrayList", out.toString());

        }
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, "some data");
        startActivity(Intent.createChooser(intent, "Open with"));


    }
}
