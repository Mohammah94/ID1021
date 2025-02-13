/*
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures, HT2021

# This code was written as part of examination of KTH course ID1021.

# The code is written by me using the textbook (Algorithms, Robert Sedgweick) & algs4.cs.princeton.edu

 */

 package Task7;

 import java.io.File;
 import java.io.FileNotFoundException;
 import java.util.NoSuchElementException;
 import java.util.Scanner;
 
 public class Assignment7<Key extends Comparable<Key>, Value> {
     private Node root;             // root of BST
 
     private class Node {
         private Key key;           // sorted by key
         private Value val;         // associated data
         private Node left, right;  // left and right subtrees
         private int size;          // number of nodes in subtree
 
         public Node(Key key, Value val, int size) {
             this.key = key;
             this.val = val;
             this.size = size;
         }
     }
 
     public Assignment7() {
     }
 
     public boolean isEmpty() {
         return size() == 0;
     }
 
 
     public int size() {
         return size(root);
     }
 
     // return number of key-value pairs in BST rooted at x
     private int size(Node x) {
         if (x == null) return 0;
         else return x.size;
     }
 
     public boolean contains(Key key) {
         if (key == null) throw new IllegalArgumentException("argument to contains() is null");
         return get(key) != null;
     }
 
     public Value get(Key key) {
         return get(root, key);
     }
 
     private Value get(Node x, Key key) {
         if (key == null) throw new IllegalArgumentException("calls get() with a null key");
         if (x == null) return null;
         int cmp = key.compareTo(x.key);
         if (cmp < 0) return get(x.left, key);
         else if (cmp > 0) return get(x.right, key);
         else return x.val;
     }
 
     public void put(Key key, Value val) {
         if (key == null) throw new IllegalArgumentException("calls put() with a null key");
         if (val == null) {
 
             return;
         }
         root = put(root, key, val);
 
     }
 
     private Node put(Node x, Key key, Value val) {
         if (x == null) return new Node(key, val, 1);
         int cmp = key.compareTo(x.key);
         if (cmp < 0) x.left = put(x.left, key, val);
         else if (cmp > 0) x.right = put(x.right, key, val);
         else x.val = val;
         x.size = 1 + size(x.left) + size(x.right);
         return x;
     }
 
 
     public Key min() {
         if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
         return min(root).key;
     }
 
     private Node min(Node x) {
         if (x.left == null) return x;
         else return min(x.left);
     }
 
 
    public Key max() {
         if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
         return max(root).key;
     }
 
     private Node max(Node x) {
         if (x.right == null) return x;
         else return max(x.right);
     }
 
     public Key select(int rank) {
         if (rank < 0 || rank >= size()) {
             throw new IllegalArgumentException("argument to select() is invalid: " + rank);
         }
         return select(root, rank);
     }
 
     private Key select(Node x, int rank) {
         if (x == null) return null;
         int leftSize = size(x.left);
         if (leftSize > rank) return select(x.left, rank);
         else if (leftSize < rank) return select(x.right, rank - leftSize - 1);
         else return x.key;
     }
 
     public Iterable<Key> keys() {
         if (isEmpty()) return new Queue<Key>();
         return keys(min(), max());
     }
 
 
     public Iterable<Key> keys(Key lo, Key hi) {
         if (lo == null) throw new IllegalArgumentException("first argument to keys() is null");
         if (hi == null) throw new IllegalArgumentException("second argument to keys() is null");
 
         Queue<Key> queue = new Queue<Key>();
         keys(root, queue, lo, hi);
         return queue;
     }
 
     private void keys(Node x, Queue<Key> queue, Key lo, Key hi) {
         if (x == null) return;
         int cmplo = lo.compareTo(x.key);
         int cmphi = hi.compareTo(x.key);
         if (cmplo < 0) keys(x.left, queue, lo, hi);
         if (cmplo <= 0 && cmphi >= 0) queue.enqueue(x.key);
         if (cmphi > 0) keys(x.right, queue, lo, hi);
     }
 
 
     public  void print() {
         print(root);
         System.out.println(" Texten är Sorterad A ==> Z ");
     }
     private void print(Node x) {
         if (x == null) return;
         print(x.left);
         System.out.println(x.key);
         print(x.right);
     }
     public  void print1() {  // reverse metoden ( z-a & Z - A )
         print1(root);
         System.out.println(" Texten är sorterad Z ==> A ");
     }
     private void print1(Node x) {
         if (x == null) return;
         print1(x.right);
         System.out.println(x.key);
         print1(x.left);
     }
    
     public static void main(String[] args) throws FileNotFoundException {
 
         Assignment7 <String, Integer> st = new Assignment7 <>();
 
         File filen = new File("text3.txt");
         Scanner scan = new Scanner(filen);
         String words ="";
         int minlen = 0;
         while(scan.hasNext()) {
             // Build symbol table and count frequencies.
             words = scan.next();   //
 
             if (words.length() < minlen) continue; // Ignore short keys.
             if (!st.contains(words))
             {
                 st.put(words.toLowerCase(), 1);
             }else {
                 st.put(words.toLowerCase(), st.get(words) + 1);
             }
         }
 
         st.print(); // Print in order
         System.out.println();
         st.print1(); // Print reverse order
         System.out.println();
       
     }
 }
 
 
 /*
 #  Name: Mohamad Abou Helal.
 
 # School: Royal Institute of Technology.
 
 # Course: Algorithms & data structures, HT2021
 
 # This code was written as part of examination of KTH course ID1021.
 
 # The code is written by me using the textbook (Algorithms, Robert Sedgweick).
 
 */
 
 package Task7;
 
 import java.util.Iterator;
 import java.util.NoSuchElementException;
 
 public class Queue<Item> implements Iterable<Item> {
 
     private Node first;            // head.
     private Node last;             // tail.
     private int N;                 // length.
 
     public Iterator<Item> iterator() {
         return new LinkedIterator(first);
     }
 
    
     private class LinkedIterator implements Iterator<Item> {
         private Node current;
 
         public LinkedIterator(Node first) {
             current = first;
         }
 
         public boolean hasNext() {
             return current != null;
         }
 
         public void remove() {
             throw new UnsupportedOperationException();
         }
 
         public Item next() {
             if (!hasNext()) throw new NoSuchElementException();
             Item item = current.item;
             current = current.next;
             return item;
         }
     }
 
     private class Node { // nested class to define nodes
         Item item;
         Node next;
         Node prev;
     }
 
     public boolean isEmpty() {
         return first == null;
     }
     // Or: N == 0.
 
     public int size() {
         return N;
     }
 
     public void enqueue(Item item) { // For adding the first node to the queue fungerar som push.
 
         if (isEmpty()) {
 
             Node newNode = new Node(); // skapa Node.
             newNode.item = item;
             last = newNode;                   //  if satatment går genom koden for En Node
             first = newNode;
         } else {
             Node newNode = new Node();
             newNode.item = item;
             last.next = newNode;               //  Annars går här köra resten av Node.
             newNode.prev = last;
             last = newNode;
         }
         N++;
     }
 
     public Item dequeue() {      // Remove item from the beginning of the list.
         Item item = first.item;
         first = first.next;
         if (isEmpty()) last = null;
         N--;
         return item;
     }
 }
 
 