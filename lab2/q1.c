/******************************************************************************

# Name: Mohamad Abou Helal.
# School: Royal Institute of Technology.
# Course: Algorithms & data structures, HT2021.
# This code was written as part of examination of KTH course ID1021.
# Task to the task is to write function in C which takes array, 
(both positive and negative) but the negative comes first the positive

*******************************************************************************/
#include <stdio.h>
void sorting (int array[], int size) 
{
 int negative = 0;
 int example = 0; // metoden att sortera negative tal before positiva.
 for (int i = 0; i < size; i++)
 {
 if (array[i] < 0)          // om element är negativa       
{
 example = array[i];  // spara i example
 array[i] = array[negative]; // byta positiva & neg 
 array[negative] = example; 
 negative++;
}
 }
}
int main ()
{
 int size = 6;
 int array[6];
 printf ("Mata in storlek : ");
 scanf ("%d", &size);
 printf ("\n");
 
 int i;
 for(i = 0; i < size; i++)
 {
 printf ("Mata in nummer :");
 scanf ("%d", &array[i]);
 }
 printf ("Array ar sorterad:\n ");
 sorting (array, size);
 for (i = 0; i < size; i++)
 {
 printf ("%d ", array[i]);
 }
}