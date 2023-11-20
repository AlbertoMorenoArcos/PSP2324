/*Crea un programa en c que reciba un número n y un número m.
El programa escribirá todos los números primos de la longitud n, utilizando m procesos.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>

int esPrimo(int numero)
{
    if (numero <= 1)
    {
        return 0; // Los números menores o iguales a 1 no son primos
    }
    for (int i = 2; i * i <= numero; i++)
    {
        if (numero % i == 0)
        {
            return 0; // El número no es primo
        }
    }
    return 1; // El número es primo
}
int realizarTrabajoDeHijo(int soy_hijo, int n_procesos, int nIntroducido)
{
    for (int i = 1; i <= nIntroducido; i++)
    {
        if (i % n_procesos == soy_hijo)
        {
            if (esPrimo(i))
            {
                printf("El numero %d es primo\n", i);
            }
            else
            {
                printf("El numero %d no es primo\n", i);
            }
        }
    }
    return 0;
}
int main()
{

    int n_procesos;
    int nIntroducido;

    int soy_hijo = 0;

    printf("Introduce un numero: \n");
    scanf("%d", &nIntroducido);

    printf("Introduce el numero de procesos: \n");
    scanf("%d", &n_procesos);
    pid_t id_hijos[n_procesos];

    for (soy_hijo = 0; soy_hijo < n_procesos; soy_hijo++)
    {
        id_hijos[soy_hijo] = fork();
        if (id_hijos[soy_hijo] == 0)
        {
            break;
        }
    }
    if (soy_hijo == n_procesos)
    {
        // Soy el padre
        for (int i = 0; i < n_procesos; i++)
        {
            wait(NULL);
        }
    }
    else
    {
        realizarTrabajoDeHijo(soy_hijo, n_procesos, nIntroducido);
    }

    return 0;
}