#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define BUFFER 1024

 /*Crea un programa en C que determine si una palabra o frase ingresada por el usuario es un palíndromo o no.
    Un palíndromo es una palabra o frase que se lee igual de izquierda a derecha y de derecha a izquierda,
    ignorando espacios y signos de puntuación.
    El programa debe eliminar los espacios y considerar solo las letras en la verificación.*/

int es_palindromo(char cadena[]) {
    int i, longitud;
    longitud = strlen(cadena);

    for (i = 0; i < longitud / 2; i++) {
        if (cadena[i] != cadena[longitud - 1 - i]) {
            return 0;
        }
    }

    return 1;
}

int main()
{
   
    char cadena[BUFFER] = "";

    printf("Introduce una cadena: ");
    fgets(cadena, sizeof(cadena), stdin);

    cadena[strcspn(cadena, "\n")] = '\0';
    if (es_palindromo(cadena)){
        printf("La cadena es un palíndromo.\n");
    }
    else{
        printf("La cadena no es un palíndromo.\n");
    }

    return 0;
}