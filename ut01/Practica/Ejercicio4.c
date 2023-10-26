/*Crea un programa que reciba un número n por parámetro.
El programa creará n hijos y les enviará una señal a cada uno de ellos para matarlos.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>

void sigusr1_handler(int signo)
{
    pid_t pid_hijo = getpid();
    printf("Voy a morir. Soy el proceso: %d\n", pid_hijo);
}
int main()
{
    int nProcesos;
    pid_t pid_hijo;
    printf("Ingrese el numero de procesos: ");
    scanf("%d", &nProcesos);

    for (int i = 0; i < nProcesos; i++)
    {
        pid_t hijo;
        hijo = fork();
        if (hijo < 0)
        {
            perror("Error al crear el primer proceso hijo");
            return 1;
        }
        else if (hijo == 0)
        {
            pid_hijo = getpid();
            signal(SIGUSR1, sigusr1_handler);
        }
        else
        {
            while (1)
            {
                sleep(1);
            }
        }
    }
    for (int i = 0; i < nProcesos; i++)
    {
        kill(pid_hijo, SIGUSR1);
    }

    return 0;
}