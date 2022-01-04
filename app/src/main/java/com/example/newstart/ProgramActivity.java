package com.example.newstart;

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
        Coding coding = new Coding();
        int[] arr = {3, 2, 1};
        ArrayList<ArrayList<Integer>> out =
                coding.findStairs(4, arr);

        Log.d("ArrayList", out.toString());
    }
}
