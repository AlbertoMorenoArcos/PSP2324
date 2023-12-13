#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>
/*
al recuperar el exit status con un wait hay que usar primero WIFEXITED
si esta devuelve true hay que usar WEXITSTATUS para saber el codigo que devuelve el hijo
*/
#include <sys/wait.h>
#include <fcntl.h>
#include <stdbool.h>
#include <string.h>
#include <signal.h>
// esto da el atoi
#include <stdlib.h>
// añadir -lm al final para compilar
#include <math.h>

#define BASE 10
#define READ 0
#define WRITE 1
#define EXTREMOS_PIPE 2
#define NUMEROS_HIJOS 2
#define BASE_DESPLAZAMIENTO 9
#define NUMERO_PARAMETROS 3
#define NUMEROS_A_ENVIAR 2
#define LONGITUD_NUMERO 1

// devuelve 1 si es primo y 0 si no es primo
int esPrimo(int numero)
{
    // inicializamos el inicio
    int contador = 2;
    // inicializamos el fin
    int fin = sqrt(numero);
    // variable de salida
    bool primo = true;

    int resultado = 0;
    // bucle de comprobacion
    while (contador <= fin && primo)
    {
        if (numero % contador == 0)
        { // modificacion de la variable de salida
            primo = false;
        }
        contador++;
    }
    if (primo)
    { // escritura de que el numero es primo
        resultado = 1;
    }
    return resultado;
}

int main(int argc, char const *argv[])
{
    int salida = EXIT_SUCCESS;
    if (argc == NUMERO_PARAMETROS)
    { // declaracion de las variables que vamos a utilizar
        int numerosAEnviar = atoi(argv[NUMEROS_A_ENVIAR]);
        int longitudNumero = atoi(argv[LONGITUD_NUMERO]);
        // boolean auxiliar para el bucle
        bool seguir = true;
        // declaracion de los pipes
        int pipesHijos[NUMEROS_HIJOS][EXTREMOS_PIPE];
        // declaracion del array donde almacenar los hijos
        pid_t hijos[NUMEROS_HIJOS];
        // identificador de los hijos
        int contadorHijos = 0;
        // contador auxiliar
        int i = 0;
        // numero que vamos a enviar a los hijos y que vamos a usar para leer
        int numAEnviar;
        while (contadorHijos < NUMEROS_HIJOS && seguir)
        {
            // creamos los pipes de los hijos
            pipe(pipesHijos[contadorHijos]);
            // cracion de los hijos
            hijos[contadorHijos] = fork();
            if (hijos[contadorHijos] == 0)
            {
                // hijo
                seguir = false;
            }
            else
            { // padre
                contadorHijos++;
            }
        }
        // identificacion de quien soy
        if (seguir)
        {
            // padre
            // cerramos los pipes que no vamos
            while (i < NUMEROS_HIJOS)
            {
                close(pipesHijos[i][READ]);
                i++;
            }
            // reseteamos el contador auxiliar
            i = 0;
            // generamos las bases del numero que deseamos generar
            int numeroBase = pow(BASE, longitudNumero - 1);
            int modulo = (BASE_DESPLAZAMIENTO * pow(BASE, longitudNumero - 1));
            srand(numAEnviar);
            // bucle para enviar los numeros
            while (i < numerosAEnviar)
            {
                // generacion del numero que vamos a enviar
                numAEnviar = (rand() % modulo) + numeroBase;
                // escribimos el numero
                write(pipesHijos[i % NUMEROS_HIJOS][WRITE], &numAEnviar, sizeof(numAEnviar));
                i++;
            }
            // reiniciamos el contador auxiliar
            i = 0;
            // cerramos los pipes de escritura
            while (i < NUMEROS_HIJOS)
            {
                close(pipesHijos[i][WRITE]);
                i++;
            }
            i = 0;
            // variable para guardar la salida del hijo
            int status;
            // bucle de espera
            while (i < NUMEROS_HIJOS)
            {
                waitpid(hijos[i], &status, 0);
                // miramos la salida del hijo
                if (WEXITSTATUS(status) == 0)
                {
                    printf("El hijo %d ha sobrevivido\n", i);
                }
                else
                {
                    printf("El hijo %d no ha sobrevivido\n", i);
                }
                i++;
            }
        }
        else
        {
            // hijo
            // cierro el pipe de escritura
            close(pipesHijos[contadorHijos][WRITE]);
            // variable para indicar nuestra salida
            bool eliminacion = false;
            // cerramos los pipes que no son nuestros
            while (i < NUMEROS_HIJOS)
            {
                if (i != contadorHijos)
                {
                    close(pipesHijos[i][READ]);
                    close(pipesHijos[i][WRITE]);
                }
                i++;
            }
            // leemos el numero que vamos a enviar
            while (read(pipesHijos[contadorHijos][READ], &numAEnviar, sizeof(numAEnviar)) > 0)
            {
                printf("Soy el hijo %d y he recibido el numero: %d\n", contadorHijos, numAEnviar);
                if (esPrimo(numAEnviar))
                {
                    eliminacion = true;
                }
            }
            // miramos que tenemos que enviar
            if (eliminacion)
            {
                exit(EXIT_FAILURE);
            }
            else
            {
                exit(EXIT_SUCCESS);
            }
        }
    }
    else
    {
        salida = EXIT_FAILURE;
    }
    return salida;
}
