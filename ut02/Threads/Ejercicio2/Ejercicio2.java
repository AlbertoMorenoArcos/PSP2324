/*Crea una clase que implemente la interfaz Runnable y sobrescriba el método run para imprimir los números del 1 al 10. 
Luego, instancia y ejecuta el hilo en la clase principal.

Modifica el programa anterior para que cree un array de N Threads y los espere. 
A cada thread le dará un nombre (Método setName) y escribirá la tabla de un número.

NOTA: La salida estará desordenada.

Ejecuta el comando en la terminal, y vuelca su salida a un fichero. 
Utiliza las redirecciones de linux y el comando sort para verificar que has escrito todas las tablas. */

package Ejercicio2;


public class Ejercicio2 {
    //

    public static void main(String[] args) {

        
        int nThreads = Integer.parseInt(args[0]);


        // Crea el array de N threads
        Thread arrayThreads[] = new Thread[nThreads];
        for (int i = 0; i < nThreads; i++) {
            arrayThreads[i] = new Thread(new numeros(i + 1));
            arrayThreads[i].setName("Tabla del " + (i + 1) + ": ");
        }
        // Inicia los threads
        for (int i = 0; i < arrayThreads.length; i++) {
            System.out.println("Thread " + arrayThreads[i].getName() + " empezado.");
            arrayThreads[i].start();
        }
        // Los espera
        for (int i = 0; i < arrayThreads.length; i++) {
            try {
                arrayThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class numeros implements Runnable {
    private static final int multiplicadores = 10;
    private int numero;

    public numeros(int numero) {
        this.numero = numero;
    }

    public void run() {
        for (int i = 1; i <= multiplicadores; i++) {
            int resultado = numero * i;
            System.out.println(numero + "*" + i + "=" + resultado);
        }
        System.out.println(String.format("Tabla del %d terminada.", numero));
    }
}