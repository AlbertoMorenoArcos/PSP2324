#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>

int main(void)
{
    pid_t id_mi_hijo; // Declaro variable para almacenar ids de procesos
    pid_t id_quepasa;
    int n = 42;
    double pi = 3.14;

    // ¡¡clonacion!!
    id_mi_hijo = fork();
    id_quepasa = fork(); 
    printf("soy el padre, mi id es %d, y mi hijo es %d y que pasa es %d \n", getpid(), id_mi_hijo, id_quepasa);
    // Hay dos procesos
    /* if (id_mi_hijo !=0)
     {
         printf("soy el padre, mi id es %d, y mi hijo es %d \n", getpid(), id_mi_hijo);
         n = 1000;
         scanf("%lf", &pi);
         printf("N vale %d \n", n);
     }else{
         printf("soy el hijo, mi id es %d, y mi hijo es %d \n", getpid(), id_mi_hijo);
         printf("N vale %d \n", n);
     }*/

    return 0;
}