#include <stdio.h>
#define BUFFER_SIZE 100

int main() {
    // Abrir el archivo en modo escritura de texto
    FILE* archivo = fopen("datos.txt", "w");

    // Comprobar si se pudo abrir el archivo
    if (archivo == NULL) {
        perror("No se pudo abrir el archivo");
        return 1;
    }

    // Escribir líneas de texto en el archivo
    fprintf(archivo, "Hola, mundo!\n");
    fprintf(archivo, "Este es un archivo de texto.\n");

    // Cerrar el archivo
    fclose(archivo);

    // Abrir el archivo en modo lectura de texto
    archivo = fopen("datos.txt", "r");

    // Comprobar si se pudo abrir el archivo
    if (archivo == NULL) {
        perror("No se pudo abrir el archivo");
        return 1;
    }

    // Leer y mostrar las líneas de texto desde el archivo
    printf("Leyendo el archivo de texto:\n");
    char linea[BUFFER_SIZE];
    while (fgets(linea, sizeof(linea), archivo) != NULL) {
        printf("%s", linea);
    }

    // Cerrar el archivo
    fclose(archivo);

    return 0;
}