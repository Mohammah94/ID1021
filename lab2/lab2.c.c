/******************************************************************************
# Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures, HT2021.

# This code was written as part of examination of KTH course ID1021.

#The code is written by me using the textbook, [InsertionSort() page 249, less() 245 from "Algorithms, Robert Sedgweick"].

*******************************************************************************/
#include <stdio.h>
#include<stdlib.h>

void sorting (int array[], int size)
{
  int j = 0;
  int example =0;
  
        for (int i = 0; i < size; i++) {
            if (array[i] < 0) {
                example = array[i];
                array[i] = array[j];
                array[j] = example;
                j++;
            }
        }
    }


int main ()
{

  int size = 6 ;
  int array;

  printf ("Mata in storlek : ");
  scanf ("%d", &size);
  printf ("\n");

  //array = (int *) malloc (size * sizeof (int));
  int i;
  for (i = 0; i < size; i++)
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
