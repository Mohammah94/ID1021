package Task4;
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
/*
# Name: Mohamad Abou Helal.
# School: Royal Institute of Technology.
# Course: Algorithms & data structures, HT2021.
# This code was written as part of examination of KTH course ID1021.
# The code is written by me using the textbook, [InsertionSort() page 249,
less() 245 from "Algorithms, Robert Sedgweick"].
*/
package Task4;
import java.util.Random;
import java.util.Scanner;
public class Question4 {
private static void merge(int[] a, int[] aux, int lo, int mid, int hi)
{ //
for (int k = lo; k <= hi; k++) {
aux[k] = a[k];
}
int i = lo, j = mid + 1;
for (int k = lo; k <= hi; k++) {
if (i > mid) a[k] = aux[j++];
else if (j > hi) a[k] = aux[i++];
else if (less(aux[j], aux[i])) a[k] = aux[j++];
else a[k] = aux[i++];
}
}
private static void sort(int[] a, int[] aux, int lo, int hi) {
if (hi <= lo) return;
int mid = lo + (hi - lo) / 2;
sort(a, aux, lo, mid);
sort(a, aux, mid + 1, hi);
merge(a, aux, lo, mid, hi);
}
public static void sort(int[] a) {
int[] aux = new int[a.length];
sort(a, aux, 0, a.length - 1);
}
private static boolean less(int v, int w) {
return v < (w);
}
public static void sort1(int[] a) {
sort(a, 0, a.length - 1);
}
private static void sort(int[] a, int lo, int hi) {
if (hi <= lo) return;
int j = partition(a, lo, hi);
sort(a, lo, j - 1);
sort(a, j + 1, hi);
}
private static int partition(int[] a, int lo, int hi) {
int i = lo;
int j = hi + 1;
int v = a[lo];
while (true) {
while (less(a[++i], v)) {
if (i == hi) break;
}
while (less(v, a[--j])) {
if (j == lo) break;
}
if (i >= j) break;
exch(a, i, j);
}
exch(a, lo, j);
return j;
}
private static boolean less1(int v, int w) {
if (v == w) return false;
return v < (w);
}
private static void exch(int[] a, int i, int j) {
int swap = a[i];
a[i] = a[j];
a[j] = swap;
}
public static void sort3(int[] array) {
int count = 0;
for (int i = 1; i < array.length; i++) {
for (int j = i; j > 0 && array[j] < array[j - 1]; j--) {
int a;
a = array[j];
array[j] = array[j - 1];
array[j - 1] = a;
count++;
}
}
}
public static void main(String[] args) {
Scanner scan = new Scanner(System.in);
Random rand = new Random();
int rand_int1 = rand.nextInt(1000);
int rand_int2 = rand.nextInt(1000);
int temp = scan.nextInt();
int[] a = new int[temp];
for (int i = 0; i < temp; i++) {
a[i] = new Random().nextInt();
}
Stopwatch timer = new Stopwatch();
sort(a);
double time = timer.elapsedTime();
System.out.print("\nMerge ==> exekverings tid är :" + "(" + time +
")");
System.out.println();
Random rand1 = new Random();
int rand_int3 = rand.nextInt(1000);
int rand_int4 = rand.nextInt(1000);
int[] b = new int[temp];
for (int i = 0; i < temp; i++) {
b[i] = new Random().nextInt();
}
Stopwatch timer1 = new Stopwatch();
sort1(b);
double time1 = timer1.elapsedTime();
System.out.print("\nQuikSort ==> exekverings tid är :" + "(" +
time1 + ")");
Random rand2 = new Random();
int rand_int5 = rand.nextInt(1000);
int rand_int6 = rand.nextInt(1000);
int[] c = new int[temp];
for (int i = 0; i < temp; i++) {
c[i] = new Random().nextInt();
}
Stopwatch timer2 = new Stopwatch();
System.out.println();
sort3(c);
double time2 = timer2.elapsedTime();
System.out.print("\nInsertion ==> Komplexitetens tid är :" + "(" +
time2 + ")");
}
}