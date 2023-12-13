#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
#include <sys/wait.h>
#include <fcntl.h>
#include <stdbool.h>
#include <string.h>
#include <signal.h>
// esto da el atoi
#include <stdlib.h>

#define READ 0
#define WRITE 1
#define EXTREMOS_PIPE 2
#define NUMEROS_HIJOS 4
#define TIEMPO_DE_SLEEP 1
#define NUMERO_PARAMETROS 2
#define NUMERO_DISPAROS 1
int vecesRecibidas = 0;
void signalHandler(int signal)
{
    if (signal == SIGUSR1)
    {
        if (vecesRecibidas > 0)
        {
            printf("Soy el hijo %d. He vuelto a ser eliminado\n", getpid());
        }
        else
        {
            printf("Soy el hijo %d y he sido eliminado\n", getpid());
            vecesRecibidas++;
        }
    }
    if (signal == SIGINT)
    {
        if (vecesRecibidas > 0)
        {
            printf("Soy el hijo %d y no he salido vivo\n", getpid());
            exit(EXIT_FAILURE);
        }
        else
        {
            printf("Soy el hijo %d y he salido vivo\n", getpid());
            exit(EXIT_SUCCESS);
        }
    }
}

int main(int argc, char const *argv[])
{

    int salida = EXIT_SUCCESS;
    if (argc == NUMERO_PARAMETROS)
    {
        // establezco los manejadores
        signal(SIGUSR1, signalHandler);
        signal(SIGINT, signalHandler);
        // saco el numero de disparos
        int numeroDisparos = atoi(argv[NUMERO_DISPAROS]);
        // variables para almacenar los numeros de hijos
        int contadorHijos = 0;
        pid_t hijos[NUMEROS_HIJOS];
        bool seguir = true;
        // creacion
        while (contadorHijos < NUMEROS_HIJOS && seguir)
        { // creo el hijo
            hijos[contadorHijos] = fork();
            if (hijos[contadorHijos] == 0)
            {
                // si soy el hijo termino el bucle
                seguir = false;
            }
            else
            {
                contadorHijos++;
            }
        }
        if (seguir)
        { // padre
            // contador auxiliar
            int i = 0;
            // hijo al que voy a disparar
            int hijo = 0;
            // hago la semilla del disparo
            srand(numeroDisparos);
            while (i < numeroDisparos)
            {
                // saco el hijo que voy a disparar
                hijo = rand() % NUMEROS_HIJOS;
                printf("voy a disparar a %d\n", hijos[hijo]);
                // disparo
                kill(hijos[hijo], SIGUSR1);
                i++;
            }
            // restablezco el contador auxiliar
            i = 0;
            while (i < NUMEROS_HIJOS)
            {
                // les digo a todos los hijos que terminen
                kill(hijos[i], SIGINT);
                i++;
            }
            // empiezo a esperar a los hijos
            i = 0;
            while (i < NUMEROS_HIJOS)
            {
                waitpid(hijos[i], NULL, 0);
                i++;
            }
        }
        else
        { // hijo
            while (true)
            { // no hago nada
                sleep(TIEMPO_DE_SLEEP);
            }
        }
    }
    else
    {
        salida = EXIT_FAILURE;
    }
    return salida;
}
