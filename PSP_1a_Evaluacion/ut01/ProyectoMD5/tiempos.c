#include <stdio.h>
#include <time.h>

#define NUM_CALC 24

// Función para calcular el factorial de un número
long long factorial(int n) {
    if (n == 0) return 1;
    return n * factorial(n - 1);
}

int main() {
    int num = NUM_CALC;  // Número para calcular el factorial

    // Variables para almacenar el tiempo de inicio y fin
    clock_t start, end;
    double cpu_time_used;

    start = clock();  // Guardamos el tiempo de inicio

    // Ejecutamos la tarea cuyo tiempo de ejecución queremos medir
    long long result = factorial(num);

    end = clock();  // Guardamos el tiempo de finalización

    // Calculamos el tiempo de ejecución en segundos
    // Tomamos la diferencia entre el tiempo de finalización y el tiempo de inicio
    // Luego dividimos por CLOCKS_PER_SEC para obtener el tiempo en segundos
    cpu_time_used = ((double) (end - start)) / CLOCKS_PER_SEC;

    printf("Factorial de %d es %lld\n", num, result);
    printf("Tiempo de ejecución: %f segundos\n", cpu_time_used);

    return 0;
}