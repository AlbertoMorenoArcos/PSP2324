#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>
#include <signal.h>
#include <math.h>
#include <sys/wait.h> // Agrega esta línea para incluir la declaración de waitpid
#include <time.h>
#include <string.h>
#include <signal.h>

#define READ 0
#define WRITE 1
#define N_PROCESOS 5
#define TRUE 1
#define FALSE 0

void sigint_handler1(int signo)
{
    printf("He sido eliminado\n");
}
void sigint_handler2(int signo)
{
    printf("He sido eliminado otra vez\n");
    exit(0);
}
int main(int argc, char *argv[])
{
    srand(time(NULL));

    int n_señales = atoi(argv[1]);

    pid_t pid_hijo;

    pid_t hijos[N_PROCESOS];

    for (int i = 0; i < N_PROCESOS; i++)
    {
        pid_t hijo = fork();

        if (hijo < 0)
        {
            perror("Error al crear el primer proceso hijo");
            return 1;
        }
        else if (hijo == 0)
        {
            pid_hijo = getpid();

            printf("Soy el hijo: %d. ", pid_hijo);
            signal(SIGUSR1, sigint_handler1);
            signal(SIGINT, sigint_handler2);
            break;
            while (1)
            {
                sleep(1);
            }
        }
        else
        {
            hijos[i] = hijo;
        }
    }
    sleep(2);
    int seguir = TRUE;
    while (seguir)
    {
        int señalRandom;
        for (int i = 0; i < n_señales; i++)
        {
            señalRandom = rand() % N_PROCESOS;
            kill(hijos[señalRandom], SIGUSR1);
        }
        for (int i = 0; i < n_señales; i++)
        {
            señalRandom = rand() % N_PROCESOS;
            kill(hijos[señalRandom], SIGINT);
        }
        if (hijos[N_PROCESOS] == 0)
        {
            seguir = FALSE;
        }
    }

    for (int i = 0; i <= N_PROCESOS; i++)
    {
        wait(NULL);
    }
    return 0;
}