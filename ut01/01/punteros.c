#include <stdio.h>
void suma_dos(int *x, int *y, int *z)
{
*x=*x+2;
*y=*y+2;
*z=*z+2;
}
void main(void){
int x;
printf("Introduzca tres numeros:");
scanf("%d %d %d", &x, &y, &z);
suma_dos (&x, &y, &z);
printf("%d %d %d", x, y, z);// qué imprimirá??
} 