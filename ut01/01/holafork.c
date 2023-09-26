#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(void) {
    pid_t id_mi_hijo; //Declaro variable para almacenar ids de procesos

    int n = 42;
    double pi = 3.14;


    //¡¡clonacion!!
    id_mi_hijo = fork();

    //Hay dos procesos
    if (id_mi_hijo !=0)
    {
        printf("soy el padre, mi id es %d, y mi hijo es %d \n", getpid(), id_mi_hijo);
        n = 1000;
        scanf("%lf", &pi);
        printf("N vale %d \n", n);
    }else{
        printf("soy el hijo, mi id es %d, y mi hijo es %d \n", getpid(), id_mi_hijo);
        printf("N vale %d \n", n);
    }
    


    return 0;
}