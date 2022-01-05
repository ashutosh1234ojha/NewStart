package com.example.newstart;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashutosh Ojha on 30,September,2021
 */
public class Coding {
    //
//    [1,0,2,5,3,9,3,6,2,5,8,2,6,2,7]
//            => 1 0 3 9 8 7
//
//
    public void getUniqueNumber() {
        int [] arr = {1, 0, 2, 5, 3, 9, 3, 6, 2, 5, 8, 2, 6, 2, 7};
        HashMap<Integer, Integer> hm = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            if (hm.containsKey(arr[i])) {
                Integer val = hm.get(arr[i]);
                hm.put(arr[i], val + 1);
            } else {
                hm.put(arr[i], 1);
            }
        }

        for (Map.Entry<Integer, Integer> entry : hm.entrySet()) {
            if (entry.getValue() == 1) {
                Log.d("Coding", entry.getKey() + " ");
            }
        }

    }

    ArrayList<ArrayList<Integer>> outter=new  ArrayList<ArrayList<Integer>>();;

     ArrayList<ArrayList<Integer>> findStairs(int n, int[] arr){

        if(n<0){
            return null;
        }
        if(n==0){
            ArrayList<Integer> in=new ArrayList<Integer>();
            ArrayList<ArrayList<Integer>> out=new  ArrayList<ArrayList<Integer>>();
            out.add(in);
            return  out;
        }
        ArrayList<ArrayList<Integer>> main=null;
         for (int j : arr) {
             int diff = n - j;
             main = findStairs(diff, arr);
             if (main != null) {
//                ArrayList<Integer> sub=main.get(0);
//                sub.add(arr[i]);
                 Log.d("StairPath", j + "");
//                main.set(0,sub);
//                int l=outter.size()-1;
//                if(l<0){
//                    outter.addAll(main);
//                }else{
//                    outter.set(l,sub);
//
//                }

//                return main;
             }
         }

        return main;
       //  return outter;
    }

}
