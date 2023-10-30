/*Crea un programa que reciba un número n por parámetro.
El programa creará n hijos y les enviará una señal a cada uno de ellos para matarlos.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>

void sigint_handler(int signo)
{
    pid_t hijo = fork();
    pid_t pid_hijo;
    if (hijo < 0)
    {
        perror("Error al crear el primer proceso hijo");
        return 1;
    }
    else if (hijo == 0)
    {
        pid_hijo = getpid();
        printf("PID hijo muerto: %d, Señal recibida: %d\n", pid_hijo, signo);
        exit(0);
    }
    else
    {

    }

    // Aquí puedes realizar acciones adicionales antes de salir si lo deseas
     //exit(0);
}
int main()
{
    signal(SIGUSR1, sigint_handler);
    
    while (1)
    {
        sleep(1);
    }
    return 0;
}