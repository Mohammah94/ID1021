/*
#  Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures, HT2021

# This code was written as part of examination of KTH course ID1021.

#The code is written by me using the textbook (Algorithms, Robert Sedgweick).

 */

 import java.util.Scanner;

 public class LabPM {
     public static void main(String[] args) {
 
         System.out.println("Skriv här");
         Scanner sc = new Scanner(System.in); // att mata in.
         String m = sc.nextLine();// Att läsa koden rad per rad.
         char[] ch = m.toCharArray();// Metoden konvertera String till char Array.
 
         int c = ch.length - 1;  // Längde är begränsad
         System.out.println("Recursive Here!");
         recursive(ch, c);       // kalla metoden!
         System.out.println();
 
         stack St = new stack(); // skapa Stack:
 
         for (int i = 0; i < ch.length; i++) {      // mata in bokstaver to stack
             St.push(ch[i]);
         }
 
         stack.Node nd = new stack.Node();
         nd = St.first;
         System.out.println(" Itrative here!");
 
         while (nd!= null){
             System.out.print(nd.ch1);   // skrivaut.
             nd = nd.next;
         }
        /* System.out.println("Pop here!");
         while(St.size>0)
         {
 
             System.out.print(St.pop().ch1); // iterative. //skriv ut
         } */
     }
 
     public static void recursive(char n[], int j) {
         if (j >= 0) {
             System.out.print(n[j]);
             j--;
             recursive(n, j);
         }
     }
 }
 /*
 #  Name: Mohamad Abou Helal.
 
 # School: Royal Institute of Technology.
 
 # Course: Algorithms & data structures HT2021
 
 # This code was written as part of examination of KTH course ID1021.
 
 #The code is written by me using the textbook (Algorithms, Robert Sedgweick).
 
  */
 
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
 
 