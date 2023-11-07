/*Crea un programa c que describa las ips que tiene en ordenador en windows*/
#include <stdio.h>
#include <unistd.h>

int main() {
    // El nombre del programa a ejecutar
    char *program = "ipconfig";

    // Argumentos para el programa: el nombre del programa, "-l", "-a" y NULL al final
    char *arguments[] = {"ipconfig", NULL};

    // Llamar a execvp para ejecutar el comando ls con argumentos
    execvp(program, arguments);

    // Si execvp falla, imprimirá un error
    perror("execvp");
    return 1;
}