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

    void createThreads() {

        new Thread(() -> {

        }).start();

        new Thread() {
            @Override
            public void run() {
                super.run();
            }
        }.start();
    }

    public void getUniqueNumber() {
        int[] arr = {1, 0, 2, 5, 3, 9, 3, 6, 2, 5, 8, 2, 6, 2, 7};
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

    ArrayList<ArrayList<Integer>> outter = new ArrayList<ArrayList<Integer>>();
    ;

    ArrayList<ArrayList<Integer>> findStairs(int n, int[] arr) {

        if (n < 0) {
            return null;
        }
        if (n == 0) {
            ArrayList<Integer> in = new ArrayList<Integer>();
            ArrayList<ArrayList<Integer>> out = new ArrayList<ArrayList<Integer>>();
            out.add(in);
            return out;
        }
        ArrayList<ArrayList<Integer>> main = null;
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

    public void knight() {
        int arr[][] = new int[5][5];

        find(arr, "", 0);

    }

    public void find(int arr[][], String asf, int r) {
        if (r >= arr.length) {
            Log.d("Output", asf);
            return;
        }

        for (int c = 0; c < arr[r].length; c++) {
            if (isQSafe(arr, r, c)) {
                arr[r][c] = 1;
                find(arr, asf + "-" + r + c, r + 1);
                arr[r][c] = 0;
            }


        }

    }

    private boolean isQSafe(int[][] arr, int r, int c) {
        //Top
        for (int i = r; i >= 0; i--) {
            if (arr[i][c] == 1) {
                return false;
            }
        }

        //Top-Left
        for (int i = r, j = c; i >= 0 && j >= 0; i--, j--) {
            if (arr[i][j] == 1) {
                return false;
            }
        }

        //Top-Right
        for (int i = r, j = c; i >= 0 && j < arr[i].length; i--, j++) {
            if (arr[i][j] == 1) {
                return false;
            }
        }
        return true;
    }


    public void knight_() {

//        for (int i = 0; i < 8; i++) {
//            for (int j = 0; j < 8; j++) {
//                int arr[][] = new int[8][8];
//                find_(arr, 0, i, j,i+""+j);
//
//
//            }
//        }
        int arr[][] = new int[8][8];
        find_(arr, 0, 0, 7,0+""+7);


    }


    public void find_(int arr[][], int count, int r, int c,String asf) {
        if (count == 64) {
            Log.d("Output", count + "Valid "+asf);
            return;
        }
        if (r >= arr.length || r < 0 || c < 0 || c >= arr[r].length || arr[r][c] != 0) {  //next move not possible
            Log.d("Output", count + "Invalid "+asf);

            return;

        }

        arr[r][c] = ++count;
        find_(arr, count, r - 2, c + 1,asf+"-"+(r-2)+(c+1));
      //  arr[r][c] = --count;

     //   arr[r][c] = ++count;
        find_(arr, count, r - 1, c + 2,asf+"-"+(r-1)+(c+2));
     //   arr[r][c] = --count;

      //  arr[r][c] = ++count;
        find_(arr, count, r + 1, c + 2,asf+"-"+(r+1)+(c+2));
       // arr[r][c] = --count;

    //    arr[r][c] = ++count;
        find_(arr, count, r + 2, c + 1,asf+"-"+(r+2)+(c+1));
    //    arr[r][c] = --count;

      //  arr[r][c] = ++count;
        find_(arr, count, r + 2, c - 1,asf+"-"+(r+2)+(c-1));
    //    arr[r][c] = --count;

    //    arr[r][c] = ++count;
        find_(arr, count, r + 1, c - 2,asf+"-"+(r+1)+(c-2));
       // arr[r][c] = --count;

      //  arr[r][c] = ++count;
        find_(arr, count, r - 1, c - 2,asf+"-"+(r-1)+(c-2));
     //   arr[r][c] = --count;


    //    arr[r][c] = ++count;
        find_(arr, count, r - 2, c - 1,asf+"-"+(r-2)+(c-1));
        arr[r][c] = 0;










//        for(int c=0;c<arr[r].length;c++){
//            if(isQSafe(arr,r,c))
//            {
//                arr[r][c]=1;
//                find(arr,asf+"-"+r+c,r+1);
//                arr[r][c]=0;
//            }
//
//        }

    }


}
