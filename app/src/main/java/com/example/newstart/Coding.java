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

    ArrayList<ArrayList<Integer>> outter=null;
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
        for(int i=0;i<arr.length;i++){
            int diff=n-arr[i];
            main=findStairs(diff,arr);
            if(main!=null){
                int l=main.size();
                ArrayList<Integer> sub=main.get(l-1);
                sub.add(arr[l-1]);
                main.set(l-1,sub);

                if(outter==null){
                    outter=main;
                }else{
                    outter.addAll(main);
                }
                return main;
            }
        }

      //  return main;
         return outter;
    }

}
