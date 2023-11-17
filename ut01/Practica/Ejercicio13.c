/*Crea un programa que reciba por parámetro dos números grandes. El programa creará dos procesos hijos.
Cada hijo gestionará un número primo y verificará si es primo o no. Cada hijo al finalizar indica en su estado si el número era primo o no
y el proceso padre al recoger el estado del hijo cuenta si era primo o no, el padre escribe el total de números primos.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>
int main(int argc, char *argv[])
{
    int num1 = atol(argv[2]);
    int num2 = atol(argv[3]);
    int nProcesos = 2;
    for (int i = 0; i < nProcesos; i++)
    {
        pid_t hijo = fork();

        if (hijo < 0)
        {
            perror("Error al crear el primer proceso hijo");
            return 1;
        }
        else if (hijo == 0)
        {

            printf("PID HIJO: %d\n", hijo);
        }
        else
        {
            wait(NULL);
        }
    }

    return 0;
}