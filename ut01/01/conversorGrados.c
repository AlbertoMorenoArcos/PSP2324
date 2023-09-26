#include <stdio.h>

int main(void){

    double celsius;
    printf("Introduzca los grados celsius para convertir: " );
    scanf("%lf", &celsius);
    double fahrenheit = (celsius * 9/5) + 32;
    printf("Los grados fahrenheit son: %lf \n" , fahrenheit);

    
    return 0;
}