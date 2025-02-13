/*
#  Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures, HT2021

# This code was written as part of examination of KTH course ID1021.

#The code is written by me using the textbook (Algorithms, Robert Sedgweick).

 */

 import java.util.Scanner;

 
 public class stack {
     public Node first;
     int size;
 
     public static class Node     // object
     {
         char ch1;
         Node next;
     }
 
     public boolean isEmpty()
     {
         return first == null;
     } // Om det är tomt retunera true!
 
     public void push (char c)   // Mata in node i stack.
     {
       Node n1 = new Node();
       n1.ch1 =  c;
       n1.next = first;
       first = n1;
       size = size+1;
     }
 
     public Node pop () {       // ta bort Node från stack.
         Node copyNode = new Node();
         copyNode = first;
         first = first.next;
         size --;
         return copyNode ;
     }
 }
 
 