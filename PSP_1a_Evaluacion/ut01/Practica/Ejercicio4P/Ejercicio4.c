/*Crea un programa que reciba un número n por parámetro.
El programa creará n hijos y les enviará una señal a cada uno de ellos para matarlos.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>

int main()
{
    int nProcesos;
    pid_t pid_hijo;

    printf("Ingrese el numero de procesos: ");
    scanf("%d", &nProcesos);
    pid_t hijos[nProcesos];

    
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
            pid_hijo = getpid();

            printf("PID HIJO: %d", pid_hijo);
            while (1)
            {
                sleep(1);
            }
        }
        else
        {
            hijos[i] = hijo;
        }
    }
    sleep(2);
    for (int i = 0; i < nProcesos; i++)
    {
        printf("Hijo muerto: %d\n", hijos[i]);
        kill(hijos[i], SIGKILL);
    }
    for (int i = 0; i < nProcesos; i++)
    {
        wait(NULL);
    }
    return 0;
}