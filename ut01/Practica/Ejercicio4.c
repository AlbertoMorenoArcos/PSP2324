/*Crea un programa que reciba un número n por parámetro. 
El programa creará n hijos y les enviará una señal a cada uno de ellos para matarlos.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>


void sigint_handler(int signo) {
    pid_t pid_hijo = getpid();
    // Aquí puedes realizar acciones adicionales antes de salir si lo deseas
    //exit(0);
        // El nombre del programa a ejecutar
    char *program = "kill";

    // Argumentos para el programa: el nombre del programa, "-l", "-a" y NULL al final
    char *arguments[] = {"kill","-9", pid_hijo};
    // Llamar a execvp para ejecutar el comando ls con argumentos
    execvp(program, arguments);

    // Si execvp falla, imprimirá un error
    perror("execvp");
    return 1;

}
int main()
{
    int nProcesos;


    printf("Ingrese el numero de procesos: ");
    scanf("%d", &nProcesos);

    for (int i = 1; i <= nProcesos; i++)
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
            signal(SIGUSR1, sigint_handler);
        }
        else
        {
            waitpid(hijo);
        }
    }
    while (1) {
        sleep(1);
    }
    return 0;
}