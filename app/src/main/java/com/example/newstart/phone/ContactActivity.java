package com.example.newstart.phone;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newstart.R;

import java.util.Arrays;

/**
 * Created by Ashutosh Ojha on 23,August,2021
 */
public class ContactActivity  extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        TextView tvGW=findViewById(R.id.tvGW);
        tvGW.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View view) {
             //   Contact.CallManagement.getInstance(ContactActivity.this).makeCall=true;

//                Contact.CallManagement.getInstance(ContactActivity.this).activateCallService();
//                Contact.CallManagement.getInstance(ContactActivity.this).onAnswerCall();
//                Contact.CallManagement.getInstance(ContactActivity.this).getNameNumber("");

//                Contact.MusicManagement.getInstance(ContactActivity.this).onPlay();
//                Contact.MusicManagement.getInstance(ContactActivity.this).onPause();
//                Contact.MusicManagement.getInstance(ContactActivity.this).onStop();

                String finalStr="Ashutos";
                String finalStr1="";
                String finalStr2=";";
                String finalStr3=null;
                byte[] bytes = finalStr.getBytes();
                byte[] bytes1 = finalStr1.getBytes();
                byte[] bytes2 = finalStr2.getBytes();
                byte[] bytes3 = finalStr3.getBytes();


                byte[] idleBytes = new byte[]{0x4A, 0x20, 0x00};
                  byte[] activeBytes = new byte[]{0x4A, 0x20, 0x01};

                addAll(idleBytes,activeBytes);

            }
        });
    }
    public static byte[] addAll(final byte[] array1, byte[] array2) {
        byte[] joinedArray = Arrays.copyOf(array1, array1.length + array2.length);
        System.arraycopy(array2, 0, joinedArray, array1.length, array2.length);
        Log.d("JoinedArray",joinedArray.toString());
        return joinedArray;
    }
}
