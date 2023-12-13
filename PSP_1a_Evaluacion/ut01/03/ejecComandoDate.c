#include <stdio.h>
#include <unistd.h>

int main() {
    // El nombre del programa a ejecutar
    char *program = "date";

    // Argumentos para el programa: el nombre del programa, "-l", "-a" y NULL al final
    char *arguments[] = {"date", NULL};

    // Llamar a execvp para ejecutar el comando ls con argumentos
    execvp(program, arguments);

    // Si execvp falla, imprimirá un error
    perror("execvp");
    return 1;
}