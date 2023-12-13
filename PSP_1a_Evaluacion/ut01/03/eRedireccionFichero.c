#include <stdio.h>
#include <unistd.h>
#include <fcntl.h>

int main()
{
    char *program = "ls";

    // Argumentos para el programa: el nombre del programa, "-l", "-a" y NULL al final
    char *arguments[] = {"ls", "/", NULL};

    // Llamar a execvp para ejecutar el comando ls con argumentos

    int file = open("output.txt", O_WRONLY | O_CREAT | O_TRUNC, 0644);
    if (file < 0)
    {
        perror("open");
        return 1;
    }

    // Redirigir la salida estándar al archivo
    dup2(file, STDOUT_FILENO); // STDOUT_FILENO es una constante que representa la salida estándar
    
    // Llamar a execvp para ejecutar el comando ls con argumentos
    // Ahora, cualquier cosa que escribamos usando execvp irá al archivo "output.txt"
    execvp(program, arguments);
    // Si execvp falla, imprimirá un error
    perror("execvp");
    // Cerrar el archivo
    close(file);

    return 0;
}