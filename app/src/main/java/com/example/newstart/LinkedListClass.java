package com.example.newstart;

import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListClass {

private static LinkedListClass linkedListClass;
  private   LinkedListClass(){
      LinkedList<String> linkedList=new LinkedList<>();
      linkedList.add("");
      ArrayList a=new ArrayList<Integer>(5);

     // Object aa=ArrayList<String>();
    }

    public synchronized   static  LinkedListClass getInstance(){
      if(linkedListClass!=null){
          return  linkedListClass;
      }else {
          linkedListClass=new LinkedListClass();
          return  linkedListClass;
      }

    }



}

