#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>

#define READ 0
#define WRITE 1

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
        int nRecibido1;
        int nRecibido2;
        int nRecibido3;


        close(fd[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer el número del pipe
        read(fd[READ], &nRecibido1, sizeof(nRecibido1));
        read(fd[READ], &nRecibido2, sizeof(nRecibido2));
        read(fd[READ], &nRecibido3, sizeof(nRecibido3));

        close(fd[READ]); // Cerrar el descriptor de lectura después de leer

        // Imprimir el número recibido
        printf("Proceso hijo recibió los números: %d,%d,%d\n", nRecibido1, nRecibido2, nRecibido3);
        // Abrir el archivo en modo escritura de texto
        FILE *archivo = fopen("salida.txt", "w");

        // Comprobar si se pudo abrir el archivo
        if (archivo == NULL)
        {
            perror("No se pudo abrir el archivo");
            return 1;
        }

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

        // Escribir líneas de texto en el archivo
        fprintf(archivo, "Los numeros aleatorios ordenados de mayor a menor son:\n Numero 1: %d\n Numero 2: %d\n Numero 3: %d\n", nRecibido3, nRecibido2, nRecibido1);
        printf("Los numeros aleatorios ordenados de mayor a menor son:\n Numero 1: %d\n Numero 2: %d\n Numero 3: %d\n", nRecibido3, nRecibido2, nRecibido1);
        // Cerrar el archivo
        fclose(archivo);
        exit(EXIT_SUCCESS);
    }
    else
    {                          // Código del proceso padre
        int n1; // Este es el número que el padre enviará al hijo
        int n2;
        int n3;
        n1 = rand() % 100; 
        n2 = rand() % 100;
        n3 = rand() % 100;
        close(fd[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        // Escribimos los números en el pipe
        write(fd[WRITE], &n1, sizeof(n1));
        write(fd[WRITE], &n2, sizeof(n2));
        write(fd[WRITE], &n3, sizeof(n3));
        close(fd[WRITE]); // Cerrar el descriptor de escritura después de escribir

        // Esperar a que el proceso hijo termine
        wait(NULL);
        printf("Proceso padre terminó\n");
    }

    return 0;
}