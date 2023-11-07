/*Modifica el anterior programa para que el hijo devuelva el resultado a través de un pipe. NOTA: debes crear dos pipes.*/
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
    int fd1[2];
    int fd2[2];
    pid_t pid;

    // Crear un pipe
    if (pipe(fd1) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    if (pipe(fd2) == -1)
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
        close(fd1[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer el número del pipe
        read(fd1[READ], &n1, sizeof(n1));
        read(fd1[READ], &n2, sizeof(n2));
        read(fd1[READ], &operando, sizeof(operando));

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
        close(fd1[READ]); // Cerrar el descriptor de lectura después de leer

        close(fd2[READ]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de LECTURA DEL SEGUNDO PIPE
        write(fd2[WRITE], &resultado, sizeof(resultado));
        close(fd2[WRITE]); // Cerramos el descriptor de escritura
        exit(0);
    }
    else
    { // Código del proceso padre
        int n1;
        int n2;
        char operando[BUFFER];
        int resultado;

        printf("Introduce el primer numero: \n");
        scanf("%d", &n1);
        printf("Introduce el operando: \n");
        scanf("%s", &operando);
        printf("Introduce el segundo numero: \n");
        scanf("%d", &n2);

        close(fd1[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        // Escribir el número en el pipe
        write(fd1[WRITE], &n1, sizeof(n1));
        write(fd1[WRITE], &n2, sizeof(n2));
        write(fd1[WRITE], &operando, sizeof(operando));
        close(fd1[WRITE]); // Cerrar el descriptor de escritura después de escribir


        close(fd2[WRITE]); // El padre no escribira en el pipe 2, así que cerramos el descriptor de lectura
        read(fd2[READ], &resultado, sizeof(resultado));
        close(fd2[READ]); // Cerramos el descriptor de lectura

        printf("PADRE: El resultado de la operación es: %d\n", resultado);
        // Esperar a que el proceso hijo termine
        wait(NULL);
    }

    return 0;
}