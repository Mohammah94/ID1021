/*
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures HT2021.

# This code was written as part of examination of KTH course ID1021.

# The code is written by me but using  from the textbook (Algorithms, Robert Sedgweick)
& taking from algs4.princten.edu/home/ as sources.

*/

package Task3;
import Task1.Graph;
import Task1.Stack;
import Task1.SymbolGraph;
import Task2.Queue;
import java.util.Scanner;

public class Assaignment3BFS {

    private static final int INFINITY = Integer.MAX_VALUE;
    private boolean[] marked;
    private int[] edgeTo;
    private int[] distTo;


    public Assaignment3BFS(Graph G, int s) {
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
        marked[s] = true;
        q.enqueue(s);

        while (!q.isEmpty()) {        // så länge är ej töm.
            int v = q.dequeue();       // dequeue sätter först i kön.
            for (int w : G.adj(v)) {
                if (!marked[w]) {
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

    public int distTo(int v) {

        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {

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
        System.out.println("Skriv från Hörn : ");
        String start = sc.next();
        System.out.println("Skriv till Hörn : ");
        String slut = sc.next();

        int begin = sg.indexOf(start);
        int end = sg.indexOf(slut);

        Assaignment3BFS bfs = new Assaignment3BFS(G, begin);

        if (bfs.distTo(end) == 1) {            // om det ej finns path
            System.out.println("inget Avstånd !!");

        } else if (!bfs.hasPathTo(end)) {
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

