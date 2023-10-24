#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <fcntl.h>
#include <string.h>

#define READ 0
#define WRITE 1

int main()
{
    int nProcesos;
    char letras[] = "abcdefghijklmnopqrstuvwxyz";

    printf("Ingrese el numero de procesos: ");
    scanf("%d", &nProcesos);

    for (int i = 1; i <= nProcesos; i++)
    {
        pid_t hijo;
        hijo = fork();
        if (hijo < 0)
        {
            perror("Error al crear el primer proceso hijo");
            return 1;
        }
        else if (hijo == 0)
        {
            char cadena[nProcesos];
            for (int i = 0; i <= strlen(letras); i++)
            {
                FILE *archivo = fopen("datos.txt", "w");
                cadena[0] = letras[i];
                cadena[1] = '\0';
                fprintf(archivo, "%s", cadena);

                // Comprobar si se pudo abrir el archivo
                if (archivo == NULL)
                {
                    perror("No se pudo abrir el archivo");
                    return 1;
                }
                

                // Cerrar el archivo
                fclose(archivo);
                printf(cadena);
            }
            exit(0);
        }
        else
        {
            waitpid(hijo);
        }
    }
    return 0;
}
/*FILE *archivo = fopen("datos.txt", "w");

        // Comprobar si se pudo abrir el archivo
        if (archivo == NULL)
        {
            perror("No se pudo abrir el archivo");
            return 1;
        }
        fprintf(archivo, "%d", i);

        // Cerrar el archivo
        fclose(archivo);*/