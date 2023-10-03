#include <stdio.h>
#include <stdlib.h>
#include <string.h>
int main(int argc, char *argv[])
{

     float n1 = atof(argv[2]);
     float n2 = atof(argv[3]);
     
    
    if (argc >= 4)
    {
   
        if (strcmp(argv[1], "suma") == 0)
        {
            float suma = n1 + n2;
            printf("Has elegido sumar %.2f + %.2f\n", n1, n2);
            printf("El resultado es: %.2f\n", suma);
        }
        else if (strcmp(argv[1], "resta") == 0)
        {
            float resta = n1 - n2;
            printf("Has elegido restar %.2f - %.2f\n", n1, n2);
            printf("El resultado es: %.2f\n", resta);
        }
        else if (strcmp(argv[1], "multiplicacion") == 0)
        {
            float multiplicacion = n1 * n2;
            printf("Has elegido multiplicar %.2f * %.2f\n", n1, n2);
            printf("El resultado es: %.2f\n", multiplicacion);
        }
        else if (strcmp(argv[1], "division") == 0)
        {
            if (n1 == 0 || n2 == 0)
            {
                printf("Uno de los dos valores es 0 y no se puede realizar la division.\n");
            }
            else
            {
                float division = n1 / n2;
                printf("Has elegido dividir %.2f / %.2f\n", n1, n2);
                printf("El resultado es: %.2f\n", division);
            }
        }
    }
    else
    {
        printf("No se proporcionaron argumentos.\n");
    }

    return 0;
}