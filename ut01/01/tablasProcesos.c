#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>

int main()
{
    int N_TABLAS = 10;
    int estado;
    pid_t id_hijo;
    for (int i = 1; i <= N_TABLAS; i++)
    {
        id_hijo = fork();

        if (id_hijo == 0)
        {
            for (int j = 1; j <= N_TABLAS; j++)
            {

                printf("%d X %d = %d\n", i, j, i * j);
            }
            exit(0);
        }
    }

    for (int i = 1; i <= N_TABLAS; i++)
    {
        wait(NULL);
    }

    return 0;
}