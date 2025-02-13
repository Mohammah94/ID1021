/*
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures HT2021.

# This code was written as part of examination of KTH course ID1021.

# The code is written by me but using  from the textbook (Algorithms, Robert Sedgweick)
& taking from algs4.princten.edu/home/ as sources.

*/
package Task5;
import Task1.Stack;
import Task4.Digraph;
import Task4.SymbolDigraph;

public class DirectedCycle {
    private boolean[] marked;        // marked[v] = has vertex v been marked?
    private int[] edgeTo;            // edgeTo[v] = previous vertex on path to v
    private boolean[] onStack;       // onStack[v] = is vertex on the stack?
    private Stack<Integer> cycle;    // vertices från a directed cycle.

    public DirectedCycle(Digraph G) {
        marked = new boolean[G.V()];
        onStack = new boolean[G.V()];
        edgeTo = new int[G.V()];
        for (int v = 0; v < G.V(); v++)
            if (!marked[v] && cycle == null) dfs(G, v); // söker efter a cycle hittas alla vertices markerad.
    }

    private void dfs(Digraph G, int v) {  // Directed path från source to V.
        onStack[v] = true;
        marked[v] = true;
        for (int w : G.adj(v)) {

            if (cycle != null) return;    // retunera om a directed cycle hittas.

            else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(G, w);
            }

            else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return cycle != null;
    }  // kontrolera om graph har a directed cycle.

    public Iterable<Integer> cycle() {
        return cycle;
    }

    public static void main(String[] args) {
        SymbolDigraph sg = new SymbolDigraph("Text", " ");
        Digraph G = sg.digraph();
        DirectedCycle dcl = new DirectedCycle(G);
        if (dcl.hasCycle()) {
            System.out.printf("Directed Cycle");
            for (int x : dcl.cycle()) {
                System.out.print(x + "  ");
            }
            System.out.println();
        } else {
            System.out.printf("No directed cycle");
        }
    }
}

