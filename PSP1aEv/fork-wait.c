#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/wait.h>
#include <stdlib.h>

int esPrimo(unsigned long n){
    int primo = 1;
    int i = 2;
    while(primo && i < n/2){
        if(n%i==0){
            primo = 0;
        }        
        i++;
    }
    return primo;
}


int main(void) {
    pid_t id_mi_hijo; // Declaro variable para almacenar ids de procesos

    unsigned long n = 18446744073709551611;

    fprintf(stdout,"Qué pereze, tengo que ver si %lu es primo. Voy a crear un hijo y que lo haga él\n", n);
    // ¡¡clonación!!!
    id_mi_hijo = fork();

    // Hay dos procesos
    if(id_mi_hijo != 0) {
        // ¿Padre?
        // Descansar hasta que el hijo finalice.
        int estado;
        wait(&estado);
        fprintf(stdout,"El hijo ha finalizado %ses primo\n", (WEXITSTATUS(estado)==1)?"":"no ");

    } else {
        // ¿Hijo?
        fprintf(stdout,"Vale papa, gracias por crearme\n");
        fprintf(stdout,"Me pondré a ver si el número es primo\n");
        int primo = esPrimo(n);
        fprintf(stdout,"¡Terminé! Te dejo en mi estado si era primo\n");
        exit(primo);
    }
    return 0;
}