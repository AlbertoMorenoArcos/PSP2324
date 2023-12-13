#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>
#include <math.h>
#include <sys/wait.h> // Agrega esta línea para incluir la declaración de waitpid
#include <time.h>

#define TAMAÑO_PIPE 2
#define READ 0
#define WRITE 1
#define PRIMO 1
#define NO_PRIMO 2
#define N_PROCESOS 2
int esPrimo(int numero)
{
    if (numero <= 1)
    {
        return 0; // Los números menores o iguales a 1 no son primos
    }
    for (int i = 2; i * i <= numero; i++)
    {
        if (numero % i == 0)
        {
            return 0; // El número no es primo
        }
    }
    return 1; // El número es primo
}

int main(int argc, char *argv[])
{
    srand(time(NULL));

    int longitudNumeros = atoi(argv[1]);
    int cantidadEnviar = atoi(argv[2]);

    int soy_hijo = 0;

    int longitudInicio = 1;
    int longitudFin = 10;
    int pipe1[TAMAÑO_PIPE];
    int pipe2[TAMAÑO_PIPE];
    pid_t hijo1, hijo2;

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
        close(pipe2[READ]); // El hijo no leerá del pipe, así que cerramos el descriptor de lectura
        int numero;
        while (read(pipe1[READ], &numero, sizeof(numero)) > 0)
        {
            printf("Soy el hijo 1 he recibido el numero: %d\n", numero);
            if (esPrimo(numero))
            {
                printf("El numero %d es Primo\n", numero);
                exit(PRIMO);
                close(pipe1[READ]);
            }
            else
            {
                printf("El numero %d no es Primo\n", numero);
            }
        }
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
            close(pipe1[WRITE]);
            close(pipe2[WRITE]);
            close(pipe1[READ]); // El hijo no leerá del pipe, así que cerramos el descriptor de lectura
            int numero;
            while (read(pipe2[READ], &numero, sizeof(numero)) > 0)
            {
                printf("Soy el hijo 2 he recibido el numero: %d\n", numero);
                if (esPrimo(numero))
                {
                    printf("El numero %d es Primo\n", numero);
                    exit(PRIMO);
                    close(pipe2[READ]);
                }
                else
                {
                    printf("El numero %d no es Primo\n", numero);
                }
            }
            // Cerrar el descriptor de lectura después de leer
        }
        else
        {
            if (longitudNumeros == longitudInicio)
            {
                longitudInicio = longitudNumeros;
            }
            else
            {
                int nPotenciaInicio = longitudNumeros - 1;
                int potenciaInicio = pow(longitudInicio, nPotenciaInicio);
                longitudInicio = potenciaInicio;
            }

            int potenciaFin = pow(longitudFin, longitudNumeros);
            longitudFin = potenciaFin - 1;

            int numeroRandom;
            for (int i = 0; i < cantidadEnviar; i++)
            {
                numeroRandom = rand() % longitudFin;
                if (esPrimo(numeroRandom))
                {
                    close(pipe1[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
                    // Escribimos los números en el pipe
                    write(pipe1[WRITE], &numeroRandom, sizeof(numeroRandom));
                }
                else
                {
                    close(pipe2[READ]); // El padre no leerá del pipe, así que cerramos el descriptor de lectura
                    // Escribimos los números en el pipe
                    write(pipe2[WRITE], &numeroRandom, sizeof(numeroRandom));
                }
            }

            close(pipe1[WRITE]); // Cerrar el descriptor de escritura después de escribir
            close(pipe2[WRITE]); // Cerrar el descriptor de escritura después de escribir

            // Esperar a que los procesos hijos terminen
            for (int i = 0; i < N_PROCESOS; i++)
            {
                sleep(1);
                // Código del padre
                int status;
                int hijo = wait(&status);

                if (WIFEXITED(status) && WEXITSTATUS(status) == PRIMO)
                {
                    printf("El hijo %d ha muerto\n", hijo);
                }
                else
                {
                    printf("El hijo %d ha sobrevivido\n", hijo);
                }
            }
        }
    }
    return 0;
}