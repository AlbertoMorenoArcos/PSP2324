#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define NOMBRE_LENGTH 50

struct Registro
{
    int numero;
    char nombre[NOMBRE_LENGTH];
};

int main()
{
    // Declarar una estructura para almacenar datos
    struct Registro datos;

    // Abrir el archivo en modo escritura binaria
    FILE *archivo = fopen("datos.bin", "wb");

    // Comprobar si se pudo abrir el archivo
    if (archivo == NULL)
    {
        perror("No se pudo abrir el archivo");
        return 1;
    }

    // Escribir datos en el archivo
    datos.numero = 65;
    strcpy(datos.nombre, "Ejemplo 1");
    fwrite(&datos, sizeof(struct Registro), 1, archivo);

    datos.numero = 169;
    strcpy(datos.nombre, "Ejemplo 2");
    fwrite(&datos, sizeof(struct Registro), 1, archivo);

    // Cerrar el archivo
    fclose(archivo);

    // Abrir el archivo en modo lectura binaria
    archivo = fopen("datos.bin", "rb");

    // Comprobar si se pudo abrir el archivo
    if (archivo == NULL)
    {
        perror("No se pudo abrir el archivo");
        return 1;
    }

    // Leer datos desde el archivo
    printf("Leyendo datos del archivo:\n");
    while (fread(&datos, sizeof(struct Registro), 1, archivo) == 1)
    {
        printf("Número: %d, Nombre: %s\n", datos.numero, datos.nombre);
    }

    // Cerrar el archivo
    fclose(archivo);

    return 0;
}