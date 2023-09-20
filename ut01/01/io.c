#include <stdio.h>

int main(void){
    
    int num1;
    int num2;
    int resultado;
    printf("Escribe el primer numero: ");
    scanf("%d",&num1);
    printf("Escribe el segundo numero: ");
    scanf("%d",&num2);

    resultado = num1+num2;

    printf("El resultado es: %d/n", resultado);

    return 0;
}