/*
#  Name: Mohamad Abou Helal.

# School: Royal Institute of Technology.

# Course: Algorithms & data structures, HT2021

# This code was written as part of examination of KTH course ID1021.

# The code is written by me. 

 */

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int main()
{
    FILE* read;   
    FILE* write;

    read = fopen("file.txt","r");     // Deklarera variabler 
    write = fopen("file2.txt","w");   
    char c = getc(read);                     // använd grtc() att läsa från file.
    while(c!= EOF)     
    {
        if(!isalpha(c) && c!='\n' )               
        {
            c = (' ');
             
        }
           putc(c,write);
            c =getc(read);
    } 
    fclose(read);                       
    fclose(write);
}   

