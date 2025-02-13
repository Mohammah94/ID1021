/*
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures, HT2021.

# This code was written as part of examination of KTH course ID1021.

# The code is written by me using the textbook, [InsertionSort() page 249, less() 245 from "Algorithms, Robert Sedgweick"].

*/
import java.util.Scanner;

public class Sorting {
    public static void main(String[] args) {

        Scanner scan1 = new Scanner(System.in);
        System.out.println(" mata in storlek : ");
        int temp = scan1.nextInt();
        int[] a = new int[temp];
        for (int i = 0; i < temp; i++) {
            System.out.print("Mata in nytt nummer :  ");
            a[i] = new Scanner(System.in).nextInt();
        }
        System.out.println("\n Array är ej sorterad:");
        for (int i = 0; i < a.length; i++) {
            System.out.print("[" + a[i] + "]");
        }
        System.out.println("\n");
        inversion(a);
        System.out.print("\n Array är sorterad ...");
        System.out.println();
        InsertionSort(a);
    }

    public static void InsertionSort(int[] array) {   // funktionen som sortera i ökande ordning

        int count = 0;
        for (int i = 1; i < array.length; i++) {          //for-Loop går igenom array & börja av det andra index.
            for (int j = i; j > 0 && array[j] < array[j - 1]; j--) { //jämför mellan enelmenter när hittas större än det aktuella värdet byta plats.
                int example;
                example = array[j];        // byta element and sätta nytt värdet.
                array[j] = array[j - 1];
                array[j - 1] = example;
                count++;
                display(array, count);
            }
        }
    }

    public static void display(int[] array, int count) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("[" + array[i] + "]");
        }
        System.out.print("  ,swap är ==> " + "(" + count + ")");
        System.out.println();
    }
    private static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    public static void inversion(int[] array) {  // funktion fråga 2..
        int counter =0;    //  räknar antal inversioner som det kommer synas i exkvering.
        for (int i = 0; i < array.length - 1; i++) {   // for-Loop går igenom array & sätta in inversioner enligt form [i,a[i]], [j, a[j]] .
            for (int j = i + 1; j < array.length; j++) {
                if (less(array[j], array[i])) {
                    System.out.println("[" + i + "," + array[i] + "] , [" + j + "," + array[j] + "]");
                    counter ++; // ökar.
                }
            }
        }
        System.out.println();
        System.out.println("Antal inversioner:  " + counter);
        System.out.println();
    }
}

