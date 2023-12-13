package PSP_1a_Evaluacion.ut02.Threads.CarreraEjercicio4;

import java.util.ArrayList;

public class Carrera {
    public static void main(String[] args) {
        Object salida = new Object();
        Object llegada = new Object();
        ArrayList<Thread> lista = new ArrayList<>();

        // Crear y empezar los hilos de los corredores
        for (int i = 0; i < Corredor.NUM_CORREDORES; i++) {
            Thread thread = new Thread(new Corredor(i + 1, salida, llegada));
            lista.add(thread);
            thread.start();
        }

        // Esperar un tiempo antes de comenzar la carrera
        try {
            Thread.sleep(Corredor.TIEMPO_ESPERA);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Notificar a todos los corredores que la carrera ha comenzado
        synchronized (salida) {
            salida.notifyAll();
        }

        // Esperar hasta que todos los corredores hayan llegado a la meta
        synchronized (llegada) {
            try {
                llegada.wait();
            } catch (InterruptedException e) {
                // Manejar excepciones de interrupción
            }
        }

        // Interrumpir cualquier corredor que aún esté en ejecución
        for (Thread thread : lista) {
            if (thread.isAlive()) {
                thread.interrupt();
            }
        }
    }
}

/*
 * Objetos compartidos (salida y llegada): Se crean dos objetos Object
 * compartidos para coordinar la salida y llegada de los corredores.
 * 
 * Lista de hilos (lista): Se crea una lista para almacenar los hilos de los
 * corredores.
 * 
 * Creación y ejecución de hilos: Se utiliza un bucle for para crear e iniciar
 * los hilos de los corredores.
 * 
 * Espera antes de la carrera: Se espera un tiempo definido
 * (Corredor.TIEMPO_ESPERA) antes de notificar a los corredores
 * para simular cierto período de preparación antes de la carrera.
 * 
 * Notificación de salida: Se utiliza synchronized (salida) para garantizar que
 * la notificación (salida.notifyAll())
 * se realice de manera segura, informando a todos los corredores que la carrera
 * ha comenzado.
 * 
 * Espera hasta la llegada: Se utiliza synchronized (llegada) y llegada.wait()
 * para que el hilo principal espere
 * hasta que todos los corredores hayan llegado a la meta.
 * 
 * Interrupción de corredores: Se itera sobre la lista de hilos y se interrumpen
 * los que aún estén en ejecución.
 */