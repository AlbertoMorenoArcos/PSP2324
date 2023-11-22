/*Crea un programa que cree dos pipes, después haga fork.
El padre enviará 3 números aleatorios al proceso hijo a través de uno de los pipes.
El proceso hijo los ordenará y los escribirá en un pipe de vuelta al padre ordenados.
El padre muestra la información y espera a que finalice el hijo.*/

#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define READ 0
#define WRITE 1

int main()
{
    int fd1[2];
    int fd2[2];
    pid_t pid;

    // Crear primer pipe
    if (pipe(fd1) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }
    // Crear segundo pipe
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
        int nRecibido1;
        int nRecibido2;
        int nRecibido3;

        close(fd1[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer el número del pipe
        read(fd1[READ], &nRecibido1, sizeof(nRecibido1));
        read(fd1[READ], &nRecibido2, sizeof(nRecibido2));
        read(fd1[READ], &nRecibido3, sizeof(nRecibido3));

        close(fd1[READ]); // Cerrar el descriptor de lectura después de leer

        // Imprimir el número recibido
        printf("Proceso hijo recibió los números: %d,%d,%d\n", nRecibido1, nRecibido2, nRecibido3);

        if (nRecibido1 > nRecibido2)
        {
            int temp = nRecibido1;
            nRecibido1 = nRecibido2;
            nRecibido2 = temp;
        }

        if (nRecibido2 > nRecibido3)
        {
            int temp = nRecibido2;
            nRecibido2 = nRecibido3;
            nRecibido3 = temp;
        }

        if (nRecibido1 > nRecibido2)
        {
            int temp = nRecibido1;
            nRecibido1 = nRecibido2;
            nRecibido2 = temp;
        }

        close(fd2[READ]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        // Escribimos los números en el pipe
        write(fd2[WRITE], &nRecibido1, sizeof(nRecibido1));
        write(fd2[WRITE], &nRecibido2, sizeof(nRecibido2));
        write(fd2[WRITE], &nRecibido3, sizeof(nRecibido3));
        close(fd2[WRITE]); // Cerrar el descriptor de escritura después de escribir

        exit(EXIT_SUCCESS);
    }
    else
    {
        srand(time(NULL));
        // Código del proceso padre
        int n1; // Este es el número que el padre enviará al hijo
        int n2;
        int n3;
        int nRecibido1;
        int nRecibido2;
        int nRecibido3;
        n1 = rand() % 100;
        n2 = rand() % 100;
        n3 = rand() % 100;
        close(fd1[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        // Escribimos los números en el pipe
        write(fd1[WRITE], &n1, sizeof(n1));
        write(fd1[WRITE], &n2, sizeof(n2));
        write(fd1[WRITE], &n3, sizeof(n3));
        close(fd1[WRITE]); // Cerrar el descriptor de escritura después de escribir

        close(fd2[WRITE]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
        read(fd2[READ], &nRecibido1, sizeof(nRecibido1));
        read(fd2[READ], &nRecibido2, sizeof(nRecibido2));
        read(fd2[READ], &nRecibido3, sizeof(nRecibido3));

        close(fd1[READ]);
        // Esperar a que el proceso hijo termine
        wait(NULL);
        printf("PADRE: Los numeros aleatorios ordenados de mayor a menor son:\n Numero 1: %d\n Numero 2: %d\n Numero 3: %d\n", nRecibido3, nRecibido2, nRecibido1);
        printf("Proceso padre terminó\n");
    }

    return 0;
}