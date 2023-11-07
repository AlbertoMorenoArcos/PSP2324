/*Crea un programa que cree un hijo y le mande a través de un pipe un carácter y dos números. El carácter representa una operación matemática: suma o resta. 
El proceso hijo devolverá en su estado el resultado de la operación.*/
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>
#define READ 0
#define WRITE 1
#define BUFFER 1024

int main()
{
    int fd[2];
    pid_t pid;

    // Crear un pipe
    if (pipe(fd) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    // Bifurcar el proceso actual para crear un proceso hijo
    pid = fork();
    if (pid == -1)
    {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (pid == 0)
    { // Código del proceso hijo
        int n1;
        int n2;
        char operando[BUFFER];
        int resultado = 0;
        close(fd[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer el número del pipe
        read(fd[READ], &n1, sizeof(n1));
        read(fd[READ], &n2, sizeof(n2));
        read(fd[READ], &operando, sizeof(operando));

        if (strcmp(operando, "+") == 0)
        {
            resultado = n1 + n2;
        }
        if (strcmp(operando, "-") == 0)
        {
            resultado = n1 - n2;
        }
        // Imprimir el número recibido
        printf("Proceso hijo recibió la cadena: %d %s %d\n", n1, operando, n2);
        printf("Resultado: %d\n", resultado);
        close(fd[READ]); // Cerrar el descriptor de lectura después de leer
        exit(resultado);
    }
    else
    { // Código del proceso padre
        int n1;
        int n2;
        char operando[BUFFER];

        printf("Introduce el primer numero: \n");
        scanf("%d", &n1);
        printf("Introduce el operando: \n");
        scanf("%s", &operando);
        printf("Introduce el segundo numero: \n");
        scanf("%d", &n2);

        close(fd[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        // Escribir el número en el pipe
        write(fd[WRITE], &n1, sizeof(n1));
        write(fd[WRITE], &n2, sizeof(n2));
        write(fd[WRITE], &operando, sizeof(operando));
        close(fd[WRITE]); // Cerrar el descriptor de escritura después de escribir

        // Esperar a que el proceso hijo termine
        wait(NULL);
    }

    return 0;
}