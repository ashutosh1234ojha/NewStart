package com.example.newstart.linkedlist;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class SingleLinkedList {

  public   Node head;
    List<Node> list;
//
//    public SingleLinkedList() {
//        list = new ArrayList<>();
//    }
//
//    public void addHead(Node node) {
//        head=node;
//    }
//    public void add(int value) {
//        Node node = new Node(value);
//        list.add(node);
//    }

    public void display(){
        Node current=head;

        while (current!=null){
           Log.d("Tag",""+current.value+"->");
            current=current.next;
        }
        Log.d("Tag","Null");
    }

    public Node reverse(Node node){
       Node current=head;
       Node previous=null;
       Node next=null;

       if(head==null){
           return  head;
       }

       while (current!=null){
           next=current.next;
           current.next=previous;
           previous=current;

           current=next;

       }
       return previous;
    }



    public static class Node {
        int value;
      public   Node next;

       public Node(int value) {
            this.value = value;
        }
//        public void next(Node value) {
//            this.next = value;
//        }
    }
}
