#include <stdio.h>
#include <math.h>
#include <math.h>

int main()
{
    float nIntroducido = 1;
    int i, factorial = 1;
    char buffer[1024];
    while (1)
    {
        nIntroducido = -1;
        printf("Ingrese un numero entero positivo: ");
        scanf("%f", &nIntroducido);
        if (nIntroducido < 0 || isnan(nIntroducido))
        {
            printf("No has introducido un numero positivo.");
            scanf("%s", buffer);
        }
        else
        {
            for (i = 1; i <= nIntroducido; i++)
            {
                factorial *= i;
                
            }
            break;
        }
        
    }
    printf("El factorial de %f es %d\n", nIntroducido, factorial);

    return 0;
}
