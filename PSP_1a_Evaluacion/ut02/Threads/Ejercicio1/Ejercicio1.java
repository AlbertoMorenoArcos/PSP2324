package Ejercicio1;
/*Hilos:
Crea una clase que extienda de Thread y sobrescriba el método run para imprimir "Hola Mundo" en la consola. 
Luego, instancia y ejecuta el hilo en la clase principal.
Crea un versión de forma que implementes Runnable.
Crea una versión con un Lambda.
Haz un método princpial que arranque los 3 threads. */


public class Ejercicio1{
    public static void main(String[] args) {
        hiloThread hiloThread = new hiloThread();
        hiloThread.start();

        Thread hiloRunnable = new Thread(new hiloRunnable());
        hiloRunnable.start();

        Runnable runnable =
        () -> { System.out.println("Hola mundo desde Lamba runnable"); };
        runnable.run();
        
    }
}

class hiloThread extends Thread{
    public void run(){
        System.out.println("Hola Mundo desde thread");
    }
}

class hiloRunnable implements Runnable {
    @Override
    public void run() {
        System.out.println("Hola Mundo desde Runnable");
    }
}
