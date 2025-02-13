/******************************************************************************
*
#  Name: Mohamad Abou Helal.
# School: Royal Institute of Technology.

# Course: Algorithms & data structures

# This code was written as part of examination of KTH course ID1021.

#The code is written by me using the textbook (Algorithms, Robert Sedgweick).
*
 */
 
#include <stdio.h>

void recursive()
{
 char m=getchar();
 if(m !='\n')
 recursive();
 putchar(m);
}

int main ()
{
    printf("Skriv här :\n");
    recursive();
    putchar('\n');
    return 0;
}


/*

#  Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures.

# This code was written as part of examination of KTH course ID1021.

#The code is written by me using the textbook (Algorithms, Robert Sedgweick).
*
 */
 
#include <stdio.h>

void function()
 {
   int length = 0;
   char c[50];
   char m;
  
   
   printf("Skriv här:\n");
   
   while((m = getchar()) != '\n'){
      
      c[length] = m;
      length++;
   }
   
   for (int i = length-1; i >= 0; i--){
       
      putchar(c[i]);
   }
   putchar('\n');
}

int main (void)
 {
    function();
    return 0;
   
 }


