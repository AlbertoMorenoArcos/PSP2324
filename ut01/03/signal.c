#include <stdio.h>
#include <stdlib.h>
#include <signal.h>
#include <unistd.h>

// Función de controlador de señal para SIGUSR1
void sigint_handler1(int signo) {
    printf("HOLA\n");
    // Aquí puedes realizar acciones adicionales antes de salir si lo deseas
    //exit(0);

}
// Función de controlador de señal para SIGUSR2
void sigint_handler2(int signo) {
    printf("MUNDO\n");
    // Aquí puedes realizar acciones adicionales antes de salir si lo deseas
    //exit(0); 
    //Si quitamos el exit(0) el proceso sigue ejecutandose y podemos seguir interactuando con el hasta que le hagamos un kill -9 que lo mata del todo

}
int main() {
    // Registrar un manejador de señales para SIGINT usando la función signal
    signal(SIGUSR1, sigint_handler1);
    signal(SIGUSR2, sigint_handler2);

    printf("MIRA COMO ESCRIBO.\n");

    // Mantén el programa en ejecución para recibir la señal
    while (1) {
        sleep(1);
    }

    return 0;
}

/*
PARA PODER RECIBIR Y EJECUTAR LA SEÑAL HAY QUE BUSCAR EL ID DEL PROCESO QUE HA CREADO EL MANEJADOR CON PS AUX | GREP ./NOMBRE_PROCESO
SE LE MANDA LA SEÑAL DESDE UN TERMINAL CON KILL -S USR1 (NºPROCESO)
KILL SIEMPRE MANDA UNA SEÑAL, CON -(Nº ENTERO DE LA SEÑAL), MANDAMOS LA SEÑAL EN CUESTION, -10 SERIA SIGUSR1 -12 SIGUSR2 EN ESTE EJEMPLO

alberto@alberto-VirtualBox:~$ ps aux | grep ./signal
alberto     4503  0.0  0.0   2772   940 pts/0    S+   17:15   0:00 ./signal
alberto     4538  0.0  0.0  11744  2540 pts/1    S+   17:15   0:00 grep --color=auto ./signal
alberto@alberto-VirtualBox:~$ kill -s USR1 4503
alberto@alberto-VirtualBox:~$ ps aux | grep ./signal
alberto     4557  0.0  0.0   2772   964 pts/0    S+   17:15   0:00 ./signal
alberto     4590  0.0  0.0  11744  2440 pts/1    S+   17:16   0:00 grep --color=auto ./signal
alberto@alberto-VirtualBox:~$ kill -s USR2 4557
alberto@alberto-VirtualBox:~$ ps aux | grep ./signal
alberto     4681  0.0  0.0   2772   936 pts/0    S+   17:20   0:00 ./signal
alberto     4716  0.0  0.0  11744  2404 pts/1    S+   17:20   0:00 grep --color=auto ./signal
alberto@alberto-VirtualBox:~$ kill -s USR2 4681
alberto@alberto-VirtualBox:~$ kill -s USR1 4681
alberto@alberto-VirtualBox:~$ kill -9  4681
*/