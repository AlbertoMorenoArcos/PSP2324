#include <stdio.h>
#include<math.h>
#include <math.h>

/*Enunciado: Crea un programa que solicite al usuario ingresar un número entero positivo y determine si es un número primo o no.

Posibles Soluciones: a. Utiliza un bucle for para verificar si el número es divisible por algún número entre 2 y la raíz cuadrada del número. */#include <stdio.h>


int main() {
float nIntroducido = 0;
int i, factorial = 1;

while(1){
    printf("Ingrese un numero entero positivo: "); 
    scanf("%f", &nIntroducido);
    if(nIntroducido < 0 || isnan(nIntroducido)){       
        printf("No has introducido un numero positivo.");
    }
    else{
        for (i = 1; i <= nIntroducido; i++){
        factorial *= i;
        break;
    }
    
}
}
printf("El factorial de %f es %d\n", nIntroducido, factorial);

return 0;
}
