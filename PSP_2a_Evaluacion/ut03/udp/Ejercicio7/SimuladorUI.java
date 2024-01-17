package PSP_2a_Evaluacion.ut03.udp.Ejercicio7;

import java.util.Scanner;

public class SimuladorUI implements Runnable {
    // pintarMenu();

    public synchronized void run() {
        try {
            obtenerSeleccion();
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int obtenerSeleccion() {
        System.out.println("1) Subir, 2) Bajar");
        Scanner sc = new Scanner(System.in);
        int opcion = sc.nextInt();
        return opcion;
    }

}
