package PSP_1a_Evaluacion.Examen1aEv.NavarroOliveraFacundo2.NavarroOliveraFacundo.ejercicio4;

import java.util.Random;

public class Cohete implements Runnable {

    private static final String FALLO_CATASTROFICO = "HA REVENTADO EL COHETE";
    private static final String FRASE_CORRECTO = "Todo avanza segun los planeado";
    private final int AVANCE = 50;
    private int distancia;
    private final double FALLO = 0.1;
    private static long TIEMPO_SLEEP = 500;
    private Random r;

    public static Object lock;
    public static Integer exito;

    // constructor de los cohetes
    public Cohete(int distancia) {
        this.distancia = distancia;
        r = new Random();
        if (lock == null) {
            lock = new Object();
        }
        if (exito == null) {
            exito = Integer.valueOf(0);
        }
    }

    @Override
    public void run() {
        try {
            // variables a utilizar
            boolean todoCorrecto = true;
            int recorrido = 0;
            double error = 0.0;
            long dormir = 0;
            // bucle de recorrido
            while (todoCorrecto && recorrido < distancia) {
                // avanzamos
                recorrido += AVANCE;
                // calculamos el tiempo que dormimos
                dormir = (r.nextLong() % TIEMPO_SLEEP) + TIEMPO_SLEEP;// r.nextLong(TIEMPO_SLEEP) + TIEMPO_SLEEP;
                // informamos que todo va bien
                System.out.println(FRASE_CORRECTO);
                // dormimos
                Thread.sleep(dormir);
                // sacamos el porcentaje de error
                error = Math.random();
                // miramos si hay error
                if (error < FALLO) {
                    // rompemos el bucle con el error
                    todoCorrecto = false;
                    // avisamos del fallo
                    System.out.println(FALLO_CATASTROFICO);
                }
            }
            // si hemos llegado
            if (todoCorrecto) {
                // aumentamos el contador de cohetes que han llegado a su fin
                synchronized (lock) {
                    exito++;
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
