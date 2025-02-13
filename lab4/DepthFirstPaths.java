/*
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures HT2021.

# This code was written as part of examination of KTH course ID1021.

# The code is written by me but using from the textbook (Algorithms, Robert Sedgweick)
 &  taking from algs4.princten.edu/home/ as sources.
 */

 package Task1;
 import java.util.Scanner;
 
 public class DepthFirstPaths {
     private boolean[] marked;     // skapa en tom array och hörn markerade som t / f.
     private int[] edgeTo;        // skapa en Töm array s- v path.
     private final int s;         // source vertex
 
     public DepthFirstPaths(Graph G, int s) {    // konstruktor
         this.s = s;
         edgeTo = new int[G.V()];
         marked = new boolean[G.V()];
         dfs(G, s);                //kalla dfs med graph and source.
     }
 
     // depth first search from v
     private void dfs(Graph G, int v) {
         marked[v] = true;          // märkera vertex(hörn) spm besöked
         for (int w : G.adj(v)) {   // loop går igenom att kolla all hörn nära V.
             if (!marked[w]) {  // om det inte är markerad.
                 edgeTo[w] = v;    //adda edge mellan dem .
                 dfs(G, w);
             }
         }
     }
 
     public boolean hasPathTo(int v) {  // om har a path retunera true.
 
         return marked[v];
     }
 
     public Iterable<Integer> pathTo(int v) {
         if (!hasPathTo(v)) return null;
         Stack<Integer> path = new Stack<Integer>();
         for (int x = v; x != s; x = edgeTo[x])
             path.push(x);
         path.push(s);
         return path;
     }
 
     public static void main(String[] args) {
 
         SymbolGraph sg = new SymbolGraph("Text", " ");  // skapa symbolgraph.
         Graph G = sg.graph();           // graph från symbolgraph.
         Scanner sc = new Scanner(System.in);
         System.out.println("Skriv från state : ");
         String start = sc.next();
         System.out.println("Skriv till state : ");
         String slut = sc.next();
 
         int begin = sg.indexOf(start); // source.
         int end = sg.indexOf(slut);
 
         DepthFirstPaths dfs = new DepthFirstPaths(G, begin);
         if (dfs.hasPathTo(end)) {    //om path är finnas
             System.out.printf("Path mellan (%s) to (%s) är : \n===> " ,sg.nameOf(begin), sg.nameOf(end));
             for (int x : dfs.pathTo(end)) {   //path använder stack.
                 if (x == begin) System.out.print(sg.nameOf(x));
                 else System.out.print("-" + sg.nameOf(x));
             }
             System.out.println();
         } else {
             System.out.printf("Ingen path mellan  (%s) to (%s) är !! ", sg.nameOf(begin), sg.nameOf(end));
         }
     }
 }
 
 package Task1;
 import java.util.Iterator;
 import java.util.NoSuchElementException;
 
 public class Bag<Item> implements Iterable<Item> {
     private Node<Item> first;    // beginning of bag
     private int n;               // number of elements in bag
 
     // helper linked list class
     private static class Node<Item> {
         private Item item;
         private Node<Item> next;
     }
 
     public Bag() {
         first = null;
         n = 0;
     }
 
     public void add(Item item) {
         Node<Item> oldfirst = first;
         first = new Node<Item>();
         first.item = item;
         first.next = oldfirst;
         n++;
     }
 
     public Iterator<Item> iterator() {
         return new LinkedIterator(first);
     }
 
     // an iterator, doesn't implement remove() since it's optional
     private class LinkedIterator implements Iterator<Item> {
         private Node<Item> current;
 
         public LinkedIterator(Node<Item> first) {
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
 }
 
 
 package Task1;
 
 public class Graph {
     private static final String NEWLINE = System.getProperty("line.separator");
     private final int V;
     private int E;
     private Bag<Integer>[] adj;   // bag innehåller array [] adj.
 
     public Graph(int V) {                                       // skapa a-vertex utan kanter
 
         this.V = V;
         this.E = 0;
         adj = new Bag[V];
         for (int v = 0; v < V; v++) {
             adj[v] = new Bag<Integer>();
         }
     }
 
     public int V() {
         return V;
     }                                       //return  hörn nummer.
 
     public void addEdge(int v, int w) {                               // tillägga edge v-w
         adj[v].add(w);
         adj[w].add(v);
         E++;
     }
 
     public Iterable<Integer> adj(int v) {
         return adj[v];
     }
 
     public String toString() {
         StringBuilder s = new StringBuilder();
         s.append(V + " vertices, " + E + " edges " + "\n");
         for (int v = 0; v < V; v++) {
             s.append(v + ": ");
             for (int w : adj[v]) {
                 s.append(w + " ");
             }
             s.append("\n");
         }
         return s.toString();
     }
 }
 
 package Task1;
 
 import java.io.BufferedInputStream;
 import java.io.File;
 import java.io.FileInputStream;
 import java.io.IOException;
 import java.io.InputStream;
 import java.net.URL;
 import java.net.URLConnection;
 import java.util.Locale;
 import java.util.NoSuchElementException;
 import java.util.Scanner;
 
 public final class In {
     private static final String CHARSET_NAME = "UTF-8";
     private static final Locale LOCALE = Locale.US;
     private Scanner scanner;
 
     public In(String name) {
         if (name == null) throw new IllegalArgumentException("argument is null");
         if (name.length() == 0) throw new IllegalArgumentException("argument is the empty string");
         try {
             // first try to read file from local file system
             File file = new File(name);
             if (file.exists()) {
                 // for consistency with StdIn, wrap with BufferedInputStream instead of use
                 // file as argument to Scanner
                 FileInputStream fis = new FileInputStream(file);
                 scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
                 scanner.useLocale(LOCALE);
                 return;
             }
 
             // resource relative to .class file
             URL url = getClass().getResource(name);
 
             // resource relative to classloader root
             if (url == null) {
                 url = getClass().getClassLoader().getResource(name);
             }
 
             // or URL from web
             if (url == null) {
                 url = new URL(name);
             }
             URLConnection site = url.openConnection();
             InputStream is = site.getInputStream();
             scanner = new Scanner(new BufferedInputStream(is), CHARSET_NAME);
             scanner.useLocale(LOCALE);
         } catch (IOException ioe) {
             throw new IllegalArgumentException("Could not open " + name, ioe);
         }
     }
 
     public boolean isEmpty() {
         return !scanner.hasNext();
     }
 
     public boolean hasNextLine() {
         return scanner.hasNextLine();
     }
 
     public String readLine() {
         String line;
         try {
             line = scanner.nextLine();
         } catch (NoSuchElementException e) {
             line = null;
         }
         return line;
     }
 }
 
 
 package Task1;
 
 import java.util.Iterator;
 import java.util.TreeMap;
 
 public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
 
     private TreeMap<Key, Value> st;
 
     public ST() {
         st = new TreeMap<Key, Value>();
     }
 
     public Value get(Key key) {
         if (key == null) throw new IllegalArgumentException("calls get() with null key");
         return st.get(key);
     }
 
     public void put(Key key, Value val) {
         if (key == null) throw new IllegalArgumentException("calls put() with null key");
         if (val == null) st.remove(key);
         else st.put(key, val);
     }
 
     public boolean contains(Key key) {
         if (key == null) throw new IllegalArgumentException("calls contains() with null key");
         return st.containsKey(key);
     }
 
     public int size() {
         return st.size();
     }
 
     public Iterable<Key> keys() {
         return st.keySet();
     }
 
     public Iterator<Key> iterator() {
         return st.keySet().iterator();
     }
 }
 
 
 package Task1;
 
 import java.util.Iterator;
 import java.util.TreeMap;
 
 public class ST<Key extends Comparable<Key>, Value> implements Iterable<Key> {
 
     private TreeMap<Key, Value> st;
 
     public ST() {
         st = new TreeMap<Key, Value>();
     }
 
     public Value get(Key key) {
         if (key == null) throw new IllegalArgumentException("calls get() with null key");
         return st.get(key);
     }
 
     public void put(Key key, Value val) {
         if (key == null) throw new IllegalArgumentException("calls put() with null key");
         if (val == null) st.remove(key);
         else st.put(key, val);
     }
 
     public boolean contains(Key key) {
         if (key == null) throw new IllegalArgumentException("calls contains() with null key");
         return st.containsKey(key);
     }
 
     public int size() {
         return st.size();
     }
 
     public Iterable<Key> keys() {
         return st.keySet();
     }
 
     public Iterator<Key> iterator() {
         return st.keySet().iterator();
     }
 }
 
 
 
 package Task1;
 
 public class SymbolGraph {
     private ST<String, Integer> st;
     private String[] keys;    // använd array till map från string till index.
     private Graph graph;
 
     public SymbolGraph(String filename, String delimiter) {
         st = new ST<String, Integer>();
         In in = new In(filename);
         // while (in.hasNextLine()) {
         while (!in.isEmpty()) {
             String[] a = in.readLine().split(delimiter);
             for (int i = 0; i < a.length; i++) {
                 if (!st.contains(a[i]))
                     st.put(a[i], st.size());
             }
         }
                                     //map från integer to string.
         keys = new String[st.size()];
         for (String name : st.keys()) {
             keys[st.get(name)] = name;
         }
                    // skapa graph inom first vertex på varje rad till alla vertices.
         graph = new Graph(st.size());
         in = new In(filename);
         while (in.hasNextLine()) {
             String[] a = in.readLine().split(delimiter);
             int v = st.get(a[0]);
             for (int i = 1; i < a.length; i++) {
                 int w = st.get(a[i]);
                 graph.addEdge(v, w);
             }
         }
     }
 
     public int indexOf(String s) {
         return st.get(s);
     }
 
     public String nameOf(int v) {
 
         return keys[v];
     }
 
     public Graph graph() {
         return graph;
     }  //retunera graph som baserad  med symbol graph.
 
 }
 
 