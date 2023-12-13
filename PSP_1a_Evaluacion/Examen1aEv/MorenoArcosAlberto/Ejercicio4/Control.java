package MorenoArcosAlberto.Ejercicio4;

import java.util.Scanner;

public class Control {

    private static final int TOTAL_KM = 408;
    private static final int START_DORSAL = 1;
    private static final int CUENTA_ATRAS = 0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Object salida = new Object();
        String CohetesArgs = args[0];
        int Cohetes = Integer.parseInt(CohetesArgs);

        Cohetes[] ArrayCohetes = new Cohetes[Cohetes];
        for (int i = 0; i < ArrayCohetes.length; i++) {
            ArrayCohetes[i] = new Cohetes(TOTAL_KM, START_DORSAL + i, salida);
        }

        Thread[] Arraythreads = new Thread[Cohetes];
        for (int i = 0; i < Arraythreads.length; i++) {
            Arraythreads[i] = new Thread(ArrayCohetes[i]);
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
