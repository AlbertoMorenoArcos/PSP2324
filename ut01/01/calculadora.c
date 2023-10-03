#include <stdio.h>

int main(int argc, char *argv[])
{

    // tolower(argv[0]);
    printf("hola");
    if (argc > 1)
    {
        if (*argv[0] == "suma")
        {
            int suma = *argv[1] + *argv[2];
            printf("Has elegido sumar %d + %d\n", argv[1], argv[2]);
            printf("El resultado es: %d", suma);
        }
        else if (argv[0] == "resta")
        {
            int resta = argv[1] - argv[2];
            printf("Has elegido restar %d - %d\n", argv[1], argv[2]);
            printf("El resultado es: %d", resta);
        }
        else if (argv[0] == "multiplicacion")
        {
            int multiplicacion = argv[1] * argv[2];
            printf("Has elegido multiplicar %d * %d\n", argv[1], argv[2]);
            printf("El resultado es: %d", multiplicacion);
        }
        else if (argv[0] == "division")
        {
            if (argv[1] == 0 || argv[2] == 0)
            {
                printf("Uno de los dos valores es 0 y no se puede realizar la division.");
            }
            else
            {
                float division = argv[1] / argv[2];
                printf("Has elegido dividir %d / %d\n", argv[1], argv[2]);
                printf("El resultado es: %2.f", division);
            }
        }
    }
    else
    {
        printf("No se proporcionaron argumentos.\n");
    }

    return 0;
}