/*
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures HT2021.

# This code was written as part of examination of KTH course ID1021.

# The code is written by me but using  from the textbook (Algorithms, Robert Sedgweick)
& taking from algs4.princten.edu/home/ as sources.

*/

package Task4;
import Task1.Stack;
import java.util.Scanner;

public class DepthFirstDirectedPaths {
    private boolean[] marked;  // boolean a marked array att DFS använd på detta vertex.
    private int[] edgeTo;      // sista vertex
    private final int s;       // först  vertex

    public DepthFirstDirectedPaths(Digraph G, int s) {
        marked = new boolean[G.V()]; // här skapats a boolean array med storlek på antalet vertices att lagra visited vertices.
        edgeTo = new int[G.V()];  // skapats en edge att lagra edge till vertices.
        this.s = s;
        dfs(G, s); // kalla DFS .
    }

    private void dfs(Digraph G, int v) { // iteration inom alla vertices till array.
        marked[v] = true;
        for (int w : G.adj(v)) {
            if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {  //loads stack för varje vertex from s till v.
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        SymbolDigraph sg = new SymbolDigraph("Text", " ");
        Digraph G = sg.digraph();
        Scanner sc = new Scanner(System.in);
        System.out.println("Skriv från hörn : ");
        String start = sc.next();
        System.out.println("Skriv till hörn : ");
        String slut = sc.next();

        int begin = sg.indexOf(start);
        int end = sg.indexOf(slut);

        DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, begin);
        if (dfs.hasPathTo(end)) {
            System.out.printf("Path mellan (%s) to (%s) är : \n===> " ,sg.nameOf(begin), sg.nameOf(end));
            for (int x : dfs.pathTo(end)) {
                if (x == begin) System.out.print(sg.nameOf(x));
                else System.out.print("-" + sg.nameOf(x));
            }
            System.out.println();
        } else {
            System.out.printf("Ingen path mellan  (%s) to (%s) är !! ", sg.nameOf(begin), sg.nameOf(end));
        }
    }
}

package Task4;
import Task1.Bag;

public class Digraph {
    private final int V;           // number of vertices in this digraph
    private int E;                 // number of edges in this digraph
    private Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v
    private int[] indegree;        // indegree[v] = indegree of vertex v


    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be non-negative");
        this.V = V;
        this.E = 0;
        indegree = new int[V];
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    public int V() {
        return V;
    }

    public void addEdge(int v, int w) {

        adj[v].add(w);
        indegree[w]++;
        E++;
    }

    public Iterable<Integer> adj(int v) {

        return adj[v];
    }

    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + '\n');
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append('\n');
        }
        return s.toString();
    }
}

package Task4;
import Task1.In;
import Task1.ST;

public class SymbolDigraph {
    private ST<String, Integer> st;  // string -> index
    private String[] keys;           // index  -> string
    private Digraph graph;             // the underlying graph

    public SymbolDigraph(String filename, String delimiter) {
        st = new ST<String, Integer>();
        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            for (int i = 0; i < a.length; i++) {
                if (!st.contains(a[i]))
                    st.put(a[i], st.size());
            }
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Digraph(st.size());
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

    public Digraph digraph() {
        return graph;
    }
}

