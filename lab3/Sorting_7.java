*
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures, HT2021.

# This code was written as part of examination of KTH course ID1021.

# The code is written by me using the textbook,[InsertionSort() page 249 from "Algorithms, Robert Sedgweick"].

*/
import java.util.Scanner;

public class Sorting_7 {
    public static void main(String[] args) {

        Scanner scan1 = new Scanner(System.in);
        System.out.println(" mata in storlek : ");
        int temp = scan1.nextInt();
        int[] a = new int[temp];
        for (int i = 0; i < temp; i++) {
            System.out.print("Mata in nytt nummer :  ");
            a[i] = new Scanner(System.in).nextInt();
        }
        System.out.print(" Array är sorterad : ");
        System.out.println();
        sort(a);
    }

    public static void sort(int[] array) {
        int count = 0;
        for (int i = 1; i < array.length; i++) {
            for (int j = i; j > 0 && array[j] * -1 < array[j - 1] * -1; j--) {
                int a;
                a = array[j];
                array[j] = array[j - 1];
                array[j - 1] = a;
                count++;
                display(array, count);
            }
        }
    }

    public static void display(int[] array, int count) {
        for (int i = 0; i < array.length; i++) {
            System.out.print("[" + array[i] + "]");
        }
        System.out.println();
    }
}

