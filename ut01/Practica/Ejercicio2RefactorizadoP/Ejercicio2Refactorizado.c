/*Crea un proceso que cree dos procesos hijos, luego generará N (20) números aleatorios. Enviará los pares al primer hijo, los impares al segundo.
Los hijos escribirán por pantalla "Soy el hijo 1|2, he recibido ". Por cada número que mande el padre.*/
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <fcntl.h>
#include <stdlib.h>
#include <sys/wait.h> // Agrega esta línea para incluir la declaración de waitpid
#include <time.h>     // Agrega esta línea para incluir la declaración de time

#define BUFFER 1024
#define READ 0
#define WRITE 1

int esPar(int numero)
{
    if (numero % 2 != 0)
    {
        return 1;
    }

    return 0;
}
int main()
{
    // Hay que utilizar una semilla para generar numeros aleatorios cada vez que se ejecuta el programa, esta utiliza la hora actual
    srand(time(NULL));
    int pipe1[2];
    int pipe2[2];
    int pipe3[2];
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
    if (pipe(pipe3) == -1)
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
        close(pipe2[READ]);
        close(pipe3[READ]); // El hijo no escribirá en el pipe, así que cerramos el descriptor de escritura
        int numero;
        int sumaHijo1;
        while (read(pipe1[READ], &numero, sizeof(numero)) > 0)
        {
            printf("Soy el hijo 1 he recibido el numero: %d\n", numero);
            sumaHijo1 += numero;
        }
        write(pipe3[WRITE], &sumaHijo1, sizeof(sumaHijo1));
        close(pipe3[WRITE]);
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
            int sumaHijo2;
            while (read(pipe2[READ], &numero, sizeof(numero)) > 0)
            {
                printf("Soy el hijo 2 he recibido el numero: %d\n", numero);
                sumaHijo2 += numero;
            }
            sleep(1);
            write(pipe3[WRITE], &sumaHijo2, sizeof(sumaHijo2));
            close(pipe3[WRITE]);
            close(pipe2[READ]); // Cerrar el descriptor de lectura después de leer
            exit(0);
        }
        else
        {
            for (int i = 1; i <= NTOTALES; i++)
            {
                numero = rand() % 100;
                if (esPar(numero))
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
            close(pipe3[WRITE]);

            close(pipe1[WRITE]); // Cerrar el descriptor de escritura después de escribir
            close(pipe2[WRITE]); // Cerrar el descriptor de escritura después de escribir

            waitpid(hijo1, NULL, 0);
            waitpid(hijo2, NULL, 0);

            int nRecibido1;
            int nRecibido2;
            read(pipe3[READ], &nRecibido1, sizeof(nRecibido1));
            read(pipe3[READ], &nRecibido2, sizeof(nRecibido2));
            close(pipe3[READ]);
            printf("Suma numeros pares: %d\n", nRecibido1);
            printf("Suma numeros impares:  %d\n", nRecibido2);
            
        }
        // Esperar a que los procesos hijos terminen
    }

    return 0;
}
