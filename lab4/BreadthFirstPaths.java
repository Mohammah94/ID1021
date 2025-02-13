/*
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures HT2021.

# This code was written as part of examination of KTH course ID1021.

# The code is written by me but using from the textbook (Algorithms, Robert Sedgweick)
& taking from algs4.princten.edu/home/ as sources.

*/

package Task2;
import Task1.Graph;
import Task1.Stack;
import Task1.SymbolGraph;
import java.util.Scanner;

public class BreadthFirstPaths {
    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;
//beräknar det kort avståndet mellan source vertex och alla andra vertices.
    public BreadthFirstPaths(Graph G, int s) {
        marked = new boolean[G.V()];
        distTo = new int[G.V()];
        edgeTo = new int[G.V()];
        bfs(G, s);
    }


    private void bfs(Graph G, int s) {
        Queue<Integer> q = new Queue<Integer>();
        for (int v = 0; v < G.V(); v++)
            distTo[v] = INFINITY;
        distTo[s] = 0;
        marked[s] = true; // node är markerad.
        q.enqueue(s);

        while (!q.isEmpty()) {          // så länge är inte tom.
            int v = q.dequeue();   // dequeue sätter först i kön
            for (int w : G.adj(v)) {
                if (!marked[w]) {   //enqueue. tillägger i kön om node är ej markerad.
                    edgeTo[w] = v;
                    distTo[w] = distTo[v] + 1;
                    marked[w] = true;
                    q.enqueue(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v) {

        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) { // antalet kanter i shortest path från source vertex till vertex.
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        int x;
        for (x = v; distTo[x] != 0; x = edgeTo[x])
            path.push(x);
        path.push(x);
        return path;
    }

    public static void main(String[] args) {
        SymbolGraph sg = new SymbolGraph("Text", " ");
        Graph G = sg.graph();
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv från hörn  : ");
        String start = sc.next();
        System.out.println("Skriv till hörn  : ");
        String slut = sc.next();

        int begin = sg.indexOf(start);
        int end = sg.indexOf(slut);

        BreadthFirstPaths bfs = new BreadthFirstPaths(G, begin);
        if (bfs.hasPathTo(end)) {
            System.out.printf("Path mellan (%s) to (%s) är :\n===> ", sg.nameOf(begin), sg.nameOf(end));
            for (int x : bfs.pathTo(end)) {
                if (x == begin) System.out.print(sg.nameOf(x));
                else System.out.print("-" + sg.nameOf(x));
            }
            System.out.println();
        } else {
            System.out.printf("Ingen path mellan  (%s) to (%s) är !! ", sg.nameOf(begin), sg.nameOf(end));
        }
    }
}

package Task2;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Queue<Item> implements Iterable<Item> {
    private Node<Item> first;    // beginning of queue
    private Node<Item> last;     // end of queue
    private int n;               // number of elements on queue

    // helper linked list class
    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Queue() {
        first = null;
        last  = null;
        n = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(Item item) {
        Node<Item> oldlast = last;
        last = new Node<Item>();
        last.item = item;
        last.next = null;
        if (isEmpty()) first = last;
        else           oldlast.next = last;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
        Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        return item;
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Item item : this) {
            s.append(item);
            s.append(' ');
        }
        return s.toString();
    }

    public Iterator<Item> iterator()  {
        return new LinkedIterator(first);
    }

    // an iterator, doesn't implement remove() since it's optional
    private class LinkedIterator implements Iterator<Item> {
        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext()  { return current != null;                     }
        public void remove()      { throw new UnsupportedOperationException();  }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }
    }
}

