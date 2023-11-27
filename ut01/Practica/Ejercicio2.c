/*Crea un proceso que cree dos procesos hijos, luego generará N (20) números aleatorios. Enviará los pares al primer hijo, los impares al segundo. 
Los hijos escribirán por pantalla "Soy el hijo 1|2, he recibido ". Por cada número que mande el padre.*/
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>

#define READ 0
#define WRITE 1

int main()
{
    //Hay que utilizar una semilla para generar numeros aleatorios cada vez que se ejecuta el programa, esta utiliza la hora actual
    srand(time(NULL)); 
    int pipe1[2];
    int pipe2[2];
    pid_t hijo1, hijo2;
    int NTOTALES = 20;
    int numero;

    // Crear un pipe
    if (pipe(pipe1) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }
    if (pipe(pipe2) == -1)
    {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    // Crear el primer proceso hijo
    hijo1 = fork();

    if (hijo1 < 0)
    {
        perror("Error al crear el primer proceso hijo");
        return 1;
    }
    else if (hijo1 == 0)
    {
        close(pipe1[WRITE]);
        close(pipe2[WRITE]);
        close(pipe2[READ]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        int numero;
        while (read(pipe1[READ], &numero, sizeof(numero)) > 0)
        {
            printf("Soy el hijo 1 he recibido el numero: %d\n", numero);
        }
        close(pipe1[READ]); // Cerrar el descriptor de lectura después de leer
        exit(0);
    }
    else
    {
        // El proceso padre continúa aquí

        // Crear el segundo proceso hijo
        hijo2 = fork();

        if (hijo2 < 0)
        {
            perror("Error al crear el segundo proceso hijo");
            return 1;
        }
        else if (hijo2 == 0)
        {
            close(pipe2[WRITE]);
            close(pipe1[WRITE]);
            close(pipe1[READ]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
            int numero;
            while (read(pipe2[READ], &numero, sizeof(numero)) > 0)
            {
                printf("Soy el hijo 2 he recibido el numero: %d\n", numero);
            }
            close(pipe2[READ]); // Cerrar el descriptor de lectura después de leer
            exit(0);
        }
        else
        {
            for (int i = 1; i <= NTOTALES; i++)
            {
                numero = rand() % 100;
                if (numero % 2 == 0)
                {
                    close(pipe1[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
                    // Escribimos los números en el pipe
                    write(pipe1[WRITE], &numero, sizeof(numero));
                }
                else
                {
                    close(pipe2[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
                    // Escribimos los números en el pipe
                    write(pipe2[WRITE], &numero, sizeof(numero));
                }
            }
            
            close(pipe1[WRITE]); // Cerrar el descriptor de escritura después de escribir
            close(pipe2[WRITE]); // Cerrar el descriptor de escritura después de escribir
        }
        // Esperar a que los procesos hijos terminen
        wait(NULL);
        wait(NULL);
    }

    return 0;
}
