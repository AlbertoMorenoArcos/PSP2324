#include <stdio.h>
#include<math.h>


/*Enunciado: Crea un programa que solicite al usuario ingresar un número entero positivo y determine si es un número primo o no.

Posibles Soluciones: a. Utiliza un bucle for para verificar si el número es divisible por algún número entre 2 y la raíz cuadrada del número. */#include <stdio.h>

int esPrimo(int numero) {
    if (numero <= 1) {
        return 0; // Los números menores o iguales a 1 no son primos
    }
    for (int i = 2; i * i <= numero; i++) {
        if (numero % i == 0) {
            return 0; // El número no es primo
        }
    }
    return 1; // El número es primo
}

int main() {
    int nIntroducido;

    printf("Introduce el numero: ");
    scanf("%d", &nIntroducido);

 
        if (esPrimo(nIntroducido)) {
            printf("El numero %d es primo\n", nIntroducido);
        }else{
            printf("El numero %d no es primo\n", nIntroducido);
        }

    return 0;
}

