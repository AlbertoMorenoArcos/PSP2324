/*Escribe un programa en C que le pida al usuario ingresar una cadena de caracteres y
luego cuente y muestre en pantalla el número de vocales (mayúsculas y minúsculas) presentes en la cadena.
El programa debe ser sensible a mayúsculas y minúsculas.*/

#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#define BUFFER 1024

int main()
{
    char cadena[BUFFER] = "";
    int mayusculas = 0;
    int minusculas = 0;

    printf("Introduce una cadena: ");
    fgets(cadena, sizeof(cadena) , stdin);
    int longitud = strlen(cadena);
    for(int i = 0; i<= longitud; i++){
        if (cadena[i] == 'a' || cadena[i] == 'e' || cadena[i] == 'i' || 
        cadena[i] == 'o' || cadena[i] == 'u'){
            minusculas++;
        }else if(cadena[i] == 'A' || cadena[i] == 'E' || cadena[i] == 'I' || 
        cadena[i] == 'O' || cadena[i] == 'U'){
            mayusculas++;
        }
    }


    printf("Las mayusculas son: %d y las minusculas son: %d\n", mayusculas, minusculas);
    return 0;
}
