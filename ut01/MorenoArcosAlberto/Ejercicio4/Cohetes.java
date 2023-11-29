package ut01.MorenoArcosAlberto.Ejercicio4;

import java.util.Random;

public class Cohetes implements Runnable {

    Random random = new Random();
    double numeroR = random.nextDouble();

    private static final long TIEMPO_DESCANSO = 500;
    private static final double MAX_INTERVALO_KM = 50;

    int kmRecorrido;
    int kmTotales;
    int nCohete;
    Object salida;

    public Cohetes(int kmTotales, int nCohete, Object salida) {
        this.kmTotales = kmTotales;

        this.kmRecorrido = 0;
        this.salida = salida;
    }

    public void run() {

        synchronized (salida) {
            try {
                salida.wait();
            } catch (Exception e) {
                System.out.println("Error en la sincronizacion");
            }
        }

        System.out.println(String.format("Soy el Cohete %d inicio mi carrera", nCohete));
        while (kmRecorrido < kmTotales) {
            try {
                long descanso = (long) (Math.random() * TIEMPO_DESCANSO + TIEMPO_DESCANSO);
                Thread.sleep(descanso);
                System.out.println(String.format("Soy el Cohete %d he descansado: %d", nCohete, descanso));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kmRecorrido += Math.random() * MAX_INTERVALO_KM;
            System.out.println(
                    String.format("Soy el Cohete %d he recorrido: %d de: %d!!", nCohete, kmRecorrido, kmTotales));
        }
        System.out.println(String.format("Soy el Cohete %d Termine!!", nCohete));

    }
}