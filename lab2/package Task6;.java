package Task6;

public class stopwatch {

    public class Stopwatch {
        private final long start;
        public Stopwatch() {
            start = System.currentTimeMillis();
        }
        public double elapsedTime() {
            long now = System.currentTimeMillis();
            return (now - start) / 1000.0;
        }
    }


}


/*
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures, HT2021.

# This code was written as part of examination of KTH course ID1021.

# The code is written by me using the textbook from "Algorithms, Robert Sedgweick"] & [algs4.cs.princeton.edu/home/.]

*/
package Task6;
import Assignmnet4.Stopwatch;

import java.util.Random;
import java.util.Scanner;

public class QuikSort6 {

    private static void recSort(int[] a, int lo, int hi) {

        int n = hi - lo + 1;
        if (n <= 8) {
            sorting(a, lo, hi);

            return;
        }

        //int m = median3(a, lo, lo + n / 2, hi);  // Kör exkvering tid med median
        int m = a[0];                            // kör exkvering tid i första element i array.
        exch(a, m, lo);

        int j = partition(a, lo, hi);

        sort(a, lo, j - 1);
        sort(a, j + 1, hi);
    }
   private static void sort(int[] a, int lo, int hi) {

        int n = hi - lo + 1;
        if (n <= 8) {
            sorting(a, lo, hi);

            return;
        }

        int m = median3(a, lo, lo + n/2, hi);
        exch(a, m, lo);

        int j = partition(a, lo, hi);

        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }
    private static int median3(int[] a, int i, int j, int k) {
        return (less(a[i], a[j]) ?
                (less(a[j], a[k]) ? j : less(a[i], a[k]) ? k : i) :
                (less(a[k], a[j]) ? j : less(a[k], a[i]) ? k : i));
    }

    private static boolean less(double v, double w) {
        return v < w;
    }

    public static void sort(int[] a) {
        recSort(a, 0, a.length - 1);

    }

    private static int partition(int[] a, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = a[lo];
        while (true) {


            while (less(a[++i], v))
                if (i == hi) break;


            while (less(v, a[--j]))
                if (j == lo) break;


            if (i >= j) break;

            exch(a, i, j);
        }


        exch(a, lo, j);


        return j;
    }

    private static boolean less(int v, int w) {
        if (v == w) return false;
        return v < (w);
    }

    private static void exch(int[] a, int i, int j) {
        int swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    public static void sorting(int[] a, int lo, int hi) {
        int size = lo - hi + 1;
        if (size <= 1)
            return;

        if (size == 2) {
            if (a [lo] > a[hi])
                exch(a, lo, hi);
            return;

        } else {

            if (a[lo] > a[hi - 1])
                exch(a, lo, hi- 1);

            if (a[lo] > a[hi])
                exch(a, lo, hi);

            if (a[hi - 1] > a[hi])
                exch(a, hi - 1, hi);
        }
    }


    public static void display(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("[" + array[i] + "]");
        }
    }

    public static void main(String[] args) {
        System.out.println("Mata in nummer :");
        Random rand = new Random();
        Scanner scan = new Scanner(System.in);
        int size = scan.nextInt();
        int[] array = new int[size];
        int[] array1 = new int[size];

        for (int i = 0; i < size; i++) {
            array[i] = rand.nextInt(1000);
            array1[i] = array[i];
        }

        Stopwatch timer = new Stopwatch();
        sort(array);
        double time = timer.elapsedTime();
        System.out.print("\nQuikSort ==> exekverings tid är :" + "(" + time + ")");
        System.out.println();

    }
}
