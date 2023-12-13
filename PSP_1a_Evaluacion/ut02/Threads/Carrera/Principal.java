package PSP_1a_Evaluacion.ut02.Threads.Carrera;

import java.util.Scanner;

public class Principal {

    private static final int TOTAL_CARRERA = 100;
    private static final int Corredores = 10;
    private static final int Hilos = 10;
    private static final int START_DORSAL = 1;
    private static final int CUENTA_ATRAS = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Object salida = new Object();
        Corredor[] Arraycorredores = new Corredor[Corredores];
        for (int i = 0; i < Arraycorredores.length; i++) {
            Arraycorredores[i] = new Corredor(TOTAL_CARRERA, START_DORSAL + i, salida);
        }

        Thread[] Arraythreads = new Thread[Hilos];
        for (int i = 0; i < Arraythreads.length; i++) {
            Arraythreads[i] = new Thread(Arraycorredores[i]);
        }

        System.out.println("La carrera va a comenzar");
        for (int i = 0; i < Arraythreads.length; i++) {
            Arraythreads[i].start();
        }
        System.out.println("Pulsa para empezar la carrera.");
        sc.nextLine();
        sc.close();
        for (int i = 10; i >= CUENTA_ATRAS; i--) {
            System.out.println(i + "!!!!");
        }
        System.out.println("SALIDA!!!!!!!");

        synchronized (salida) {
            try {
                salida.notifyAll();
            } catch (Exception e) {
                System.out.println("Error en la sincronizacion");
            }
        }
        for (int i = 0; i < Arraythreads.length; i++) {
            try {
                Arraythreads[i].join();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("La carrera ha terminado");
    }
}
