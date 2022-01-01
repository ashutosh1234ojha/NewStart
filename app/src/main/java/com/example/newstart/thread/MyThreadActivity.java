package com.example.newstart.thread;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.newstart.R;

/**
 * Created by Ashutosh Ojha on 03,September,2021
 */
public class MyThreadActivity extends AppCompatActivity {
    int  count=0;
    Thread odd,even;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn1=findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                odd.start();
//                even.start();
//             MyClass myThread=new   MyClass();
//                int arr[] = {3, 5, 6, 9, 8, 2, 1, 4};
//             MyClass.fun(arr);
                checkOpps();
            }
        });

         odd=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized(even) {
                        even.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("MyThreadActivity",++count+"");
                even.notify();
            }
        });

         even=new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized(odd) {
                        odd.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Log.d("MyThreadActivity",++count+"");

                    odd.notify();

            }
        });
    }

    private void checkOpps() {
//        ChildClass childClass=new ChildClass();
//        ParentClass parentClass=new ParentClass();
//        ParentClass childObj=new ChildClass();
//
//        Log.d("checkOpps",childClass.a+"");
//        Log.d("checkOpps",parentClass.a+"");
//        Log.d("checkOpps",childObj.a+"");
//
//
//        childClass.fun();
//        parentClass.fun();
//        childObj.fun();
    }
}
