package com.example.newstart;

import android.util.Log;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;

public class MyClass {
    int a;

//    static ab(){
//        static
//    }

    public Long fib(Long n, HashMap<Long, Long> hm) {
        if (hm.containsKey(n)) {
            return hm.get(n);
        }

        if (n <= 2) return 1L;

        hm.put(n, fib(n - 2, hm) + fib(n - 1, hm));
        return hm.get(n);
    }

    public int add() {

        String str = "hello";
        System.out.println("before " + str.hashCode());
        str = "hi";
        System.out.println("after " + str.hashCode());

        String str1 = "hello";
        System.out.println("str1 " + str1.hashCode());

        String str2 = new String("my");
        System.out.println("str2 " + str2.hashCode());

        String str3 = new String("my");
        System.out.println("str3 " + str3.hashCode());
        if (str2 == str3) {
            System.out.println("str2==str3 " + true);

        } else {
            System.out.println("str2==str3 " + false);

        }

        String str4 = "my";

        System.out.println("str4 " + str4.hashCode());


        int arr[] = {3, 1, 3, 3, 2};
        int n = 5;

        String str5 = new String("my");

        MySeal.ab();

        HashMap<Integer, Integer> hashMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            if (hashMap.containsKey(arr[i])) {
                int val = hashMap.get(arr[i]);
                hashMap.put(arr[i], val + 1);
            } else {
                hashMap.put(arr[i], 1);

            }
        }

        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            if (entry.getValue() >= n / 2) {
                return entry.getKey();
            }
        }

        return -1;


    }

    public void a1() {
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(1);
        hashSet.add(2);
        hashSet.remove(2);


        System.out.println(hashSet.isEmpty());
        System.out.println(hashSet.contains(2));
        System.out.println(hashSet.size());

        Iterator<Integer> iterator = hashSet.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }


    }


//    int longestCommonSum( int n) {
//         boolean[] arr1 = {false,true,false,false,false,false};
//        boolean[] arr2 = {true,false,true,false,false,true};
//        int longe=-1;
//        int len=0;
//        for(int i=0;i<n;i++){
//            int sum1=0, sum2=0;
//            for(int j=i;j<n;j++){
//                sum1=sum1 + getValue(arr1[j]);
//                sum2=sum2+getValue(arr2[j]);;
//
//                if(sum1==sum2&&sum1>longe){
//                    longe=sum1;
//                    len=j+1-i;
//
//                    System.out.println("n "+n);
//                    System.out.println("Longest "+longe);
//                    System.out.println("Length "+len);
//                    System.out.println("i "+i);
//                    System.out.println("j "+j);
//
//
//
//                }
//
//            }
//        }
//        return len;
//    }

    int longestCommonSum(int n) {

        boolean[] arr1 = {false, true, false};
        boolean[] arr2 = {true, false, true};
        // int longe=-1;
        int len = 0;
        for (int i = 0; i < n; i++) {
            int sum1 = 0, sum2 = 0;
            for (int j = i; j < n; j++) {
                sum1 = sum1 + getValue(arr1[j]);
                sum2 = sum2 + getValue(arr2[j]);
                ;


                if (sum1 == sum2) {
                    int l = j - i + 1;
                    if (l > len) {
                        len = l;
                    }

                    System.out.println("n " + n);
                    System.out.println("Length " + len);
                    System.out.println("i " + i);
                    System.out.println("j " + j);


                }

            }
        }
        return len;
    }

    int getValue(boolean bol) {
        if (bol) {
            return 1;
        } else {
            return 0;
        }
    }

//    int doUnion()  {
//        int a[]={85, 25 ,1, 32, 54, 6};
//            int b[]={    85, 2};
//        int l1=a.length, l2=b.length;
//        int union[] =new int[l1+l2];
//
//        int i=0,j=0,k=0;
//        while(i<l1||j<l2){
//            if(i<l1&&j<l2&&a[i]==b[j]){
//
//                    union[k]=a[i];
//
//                k++;
//            }else{
//                if(i<l1)
//                {
//                    union[k]=a[i];
//                    k++;
//
//                }
//
//
//                if(j<l2)
//                {
//                    union[k]=b[j];
//                    k++;
//
//                }
//
//
//            }
//
//            if(i<l1)
//                i++;
//
//            if(j<l2)
//                j++;
//
//        }
//
//        return union.length;
//        //code here
//    }


    public static int doUnion(int a[], int n, int b[], int m) {
        //Your code here


        // int a[]={85, 25 ,1, 32, 54, 6};
        //     int b[]={    85, 2};
        int l1 = a.length, l2 = b.length;
        int union[] = new int[l1 + l2];

        int i = 0, j = 0, k = 0;
        while (i < l1 || j < l2) {
            if (i < l1 && j < l2 && a[i] == b[j]) {

                union[k] = a[i];

                k++;
            } else {
                if (i < l1) {
                    union[k] = a[i];
                    k++;

                }


                if (j < l2) {
                    union[k] = b[j];
                    k++;

                }


            }

            if (i < l1)
                i++;

            if (j < l2)
                j++;

        }

        return union.length;
        //code here

    }


    private boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        int n = s.length();
        while (i <= j) {
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            i++;
            j--;
        }
        return true;


    }

    void substrCount(int n, String s) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                String sub = s.substring(i, j);
                if (isPalindrome(sub)) {
                    count++;
                }


            }
        }
        System.out.println("Hello, World!" + count);
        Log.d("TAg", count + "  jjj");
    }


    int minDist(int a[], int n, int x, int y) {
        // code here
        int l = -1;
        int xf = -1, yf = -1;


        for (int i = 0; i < n; i++) {
            if (xf == -1 && yf == -1) {

                if (a[i] == x) {
                    xf = i;
                }
                if (a[i] == y) {
                    yf = i;
                }

            } else if (xf == -1)
                if (a[i] == x) {
                    xf = i;
                    int t = xf - yf;
                    if (t < l) {
                        l = t;
                    }
                    if (a[i] == y) {
                        yf = i;


                    }
                } else if (yf == -1) {
                    if (a[i] == y) {
                        yf = i;
                        int t = yf - xf;
                        if (t < l) {
                            l = t;
                        }
                    }
                    if (a[i] == x) {
                        xf = i;

                    }
                } else {
                    if (a[i] == y) {
                        yf = i;
                        int t = yf - xf;
                        if (t < l) {
                            l = t;
                        }

                    }
                    if (a[i] == x) {
                        xf = i;
                        int t = xf - yf;
                        if (t < l) {
                            l = t;
                        }

                    }


                }
        }

        return l;
    }

    public static void fun(int arr[]) {
        int h = 0, s = 0;
        int n = arr.length;
        for (int i = 0; i < n; i++) {
            int val = Math.abs(arr[i]);
            if (val == 9) {
                s = 9;
            } else if (val == 10) {
                h = 10;
            } else {
                int temp = arr[val];
                arr[val - 1] = -temp;
            }
        }
        for (int i = 0; i < n; i++) {

            System.out.println("" + arr[i]);

        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] > 0) {
                count++;
                System.out.println("" + (i + 1));
            }
        }
        if (count == 1) {
            if (h != 0) {
                System.out.println("" + h);
            } else {
                System.out.println("" + s);
            }
        } else if (count == 0) {
            System.out.println("" + s);
            System.out.println("" + h);
        }
    }

}

