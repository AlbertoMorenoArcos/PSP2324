#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include<ctype.h>
#define BUFFER 1024

 /*Crea un programa en C que determine si una palabra o frase ingresada por el usuario es un palíndromo o no.
    Un palíndromo es una palabra o frase que se lee igual de izquierda a derecha y de derecha a izquierda,
    ignorando espacios y signos de puntuación.
    El programa debe eliminar los espacios y considerar solo las letras en la verificación.*/

int main(void)
{
char frase[BUFFER];
int esPalindromo = 1;
fprintf(stdout,"Introduce una frase: ");
scanf(" %[^\n]", frase);

int longitudFrase = strlen(frase);

for (int i = 0, j = longitudFrase - 1; i < j ; i++, j-- ){
    while(isspace(frase[i])){
        i++;
    }
    while(isspace(frase[j])){
        j--;
    }
    if(tolower(frase[i]) != tolower(frase[j])){
        esPalindromo = 0; 
        break;
    }
}   

if(esPalindromo == 1){
    printf("La frase: %s --- es un palindromo\n", frase);
}else{
    printf("La frase: %s --- no es un palindromo\n", frase);
}


    return 0;
}