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
        int numero_recibido;
        int cubo;
        close(fd[WRITE]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura

        // Leer el número del pipe
        read(fd[READ], &numero_recibido, sizeof(numero_recibido));
        close(fd[READ]); // Cerrar el descriptor de lectura después de leer

        cubo = numero_recibido * numero_recibido * numero_recibido;
        // Imprimir el número recibido
        printf("Proceso hijo recibió el número: %d\n", numero_recibido);
        printf("El cubo es: %d\n", cubo);
        // Abrir el archivo en modo escritura de texto
        FILE *archivo = fopen("salida.txt", "w");

        // Comprobar si se pudo abrir el archivo
        if (archivo == NULL)
        {
            perror("No se pudo abrir el archivo");
            return 1;
        }

        // Escribir líneas de texto en el archivo
        fprintf(archivo, "Hola, soy el hijo, el cubo es: %d", cubo);

        // Cerrar el archivo
        fclose(archivo);
        exit(EXIT_SUCCESS);
    }
    else
    {                        // Código del proceso padre
        int numero_a_enviar; // Este es el número que el padre enviará al hijo
        printf("Escribe un numero: ");
        scanf("%d", &numero_a_enviar);
        close(fd[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura

        // Escribir el número en el pipe
        write(fd[WRITE], &numero_a_enviar, sizeof(numero_a_enviar));
        close(fd[WRITE]); // Cerrar el descriptor de escritura después de escribir

        // Esperar a que el proceso hijo termine
        wait(NULL);
        printf("Proceso padre terminó\n");
    }

    return 0;
}