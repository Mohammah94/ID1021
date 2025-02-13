package Task5;
public class Stopwatch {
private final long start;
public Stopwatch() {
start = System.currentTimeMillis();
}
public double elapsedTime() {
long now = System.currentTimeMillis();
return (now - start) / 1000.0;
}
/*
# Name: Mohamad Abou Helal.
# School: Royal Institute of Technology.
# Course: Algorithms & data structures, HT2021.
# This code was written as part of examination of KTH course ID1021.
# The code is written by me using the textbook from "Algorithms, Robert
Sedgweick" & link [algs4.cs.princeton.edu/home/.]
*/
package Task5;
import java.util.Random;
import java.util.Scanner;
public class CutOff {
private static final int CUTOFF = 30; // bestämma rang mellan [0-30]
private static void merge(int[] src, int[] dst, int lo, int mid, int
hi) { // Merge funktion.
int i = lo, j = mid + 1;
for (int k = lo; k <= hi; k++) {
if (i > mid) dst[k] = src[j++];
else if (j > hi) dst[k] = src[i++];
else if (less(src[j], src[i])) dst[k] = src[j++];
else dst[k] = src[i++];
}
}
private static void sort(int[] src, int[] dst, int lo, int hi) {
//Sort funktionen
if (hi <= lo + CUTOFF) {
insertionSort(dst, lo, hi);
return;
}
int mid = lo + (hi - lo) / 2;
sort(dst, src, lo, mid);
sort(dst, src, mid + 1, hi);
if (!less(src[mid + 1], src[mid])) {
    System.arraycopy(src, lo, dst, lo, hi - lo + 1);
    return;
    }
    merge(src, dst, lo, mid, hi);
    }
    private static void exch(int[] a, int i, int j) {
    int swap = a[i];
    a[i] = a[j];
    a[j] = swap;
    }
    private static boolean less(Comparable a, Comparable b) {
    return a.compareTo(b) < 0;
    }
    public static void sort(int[] a) {
    int[] aux = a.clone();
    sort(aux, a, 0, a.length - 1);
    }
    private static void insertionSort(int[] a, int lo, int hi) {
    for (int i = lo; i <= hi; i++)
    for (int j = i; j > lo && less(a[j], a[j - 1]); j--)
    exch(a, j, j - 1);
    }
    private static void display(int[] a) {
    for (int i = 0; i < a.length; i++) {
    System.out.println(a[i]);
    }
    }
    public static void main(String[] args) {
    Scanner scan1 = new Scanner(System.in);
    Random rand = new Random();
    int rand_int1 = rand.nextInt(1000);
    int rand_int2 = rand.nextInt(1000);
    int example = scan1.nextInt();
    int[] a = new int[example];
    for (int i = 0; i < example; i++) {
    a[i] = new Random().nextInt();
    /*int a[]= {1,-3,0, 9, 6,-10,8,2,-4,7,4}; // test koden om
    funktionen funkar eller ej.
    sort(a);
    display(a);*/
    }
    Stopwatch timer = new Stopwatch();
    sort(a);
    double time = timer.elapsedTime();
    System.out.print("\nExecution time with cutoff:" + "(" + time + "
    s)"+ "Rang är" + "["+CUTOFF+"]");
    System.out.println();
    }
}