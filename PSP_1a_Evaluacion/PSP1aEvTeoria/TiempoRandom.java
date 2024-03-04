package PSP_1a_Evaluacion.PSP1aEvTeoria;

import java.util.Random;

public class TiempoRandom {
    public static void main(String[] args) {
        try {
            // Creamos una instancia de la clase Random
            Random random = new Random();

            // Generamos un número aleatorio entre 100 y 500
            int tiempoAleatorio = random.nextInt(401) + 100;

            // Dormimos el hilo durante el tiempo aleatorio
            Thread.sleep(tiempoAleatorio);

            // Imprimimos un mensaje después de esperar el tiempo aleatorio
            System.out.println("Han pasado " + tiempoAleatorio + " milisegundos.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
