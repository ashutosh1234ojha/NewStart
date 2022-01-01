package com.example.newstart;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Ashutosh Ojha on 21,August,2021
 */
public class Tst {
    StringBuilder str=new StringBuilder();
    HashMap<String,Integer>hm=new HashMap<String,Integer>();

    public static String isValid(String s) {
        // Write your code here
        String op="NO";
        HashMap<String,Integer>hm=new HashMap<String,Integer>();
        for(int i=0;i<s.length();i++){
            if(hm.containsKey(Character.toString(s.charAt(i)))){
                hm.put(Character.toString(s.charAt(i)),hm.get(s.charAt(i))+1);
            }else{
                hm.put(Character.toString(s.charAt(i)),1);
            }
        }
        int two=0;
        for(Map.Entry<String,Integer>entry : hm.entrySet()){
            int value=entry.getValue();
            if(value>2){
                return "NO";
            }
            System.out.println("Vl "+value);
            if(value==2){
                two=two+1;
                System.out.println(value);
            }

        }
        if(two>1){
            return "NO";
        }

        return "YES";
    }

    void stringReverse(String str){
        StringBuilder sb=new StringBuilder(str);
        int i=0;
        int j=sb.length();



        while(i<j){
        }

    }
}
