package CarreraEjercicio4;

public class Corredor implements Runnable {
    int dorsal;
    int distanciaRecorrida;
    Long tiempo = null;
    static final int DISTANCIA_TOTAL = 100, INTERVALO = 10, NUM_CORREDORES = 10;
    static final long TIEMPO_ESPERA = 500;
    private static final String MSG_PERDEDOR = "Soy el perdedor (nº%d) y me he quedado en el kilómetro %d. Tiempo: %d milisegundos.\n";
    Object salida;
    Object llegada;

    public Corredor(int num, Object salida, Object llegada) {
        this.dorsal = num;
        this.salida = salida;
        this.llegada = llegada;
        this.distanciaRecorrida = 0;
    }

    public void run() {
        long tiempoLlegada = 0;
        long tiempoInicio = 0;

        try {
            // Espera la señal de salida
            synchronized (salida) {
                salida.wait();
            }

            tiempoInicio = System.currentTimeMillis();

            // Simula el recorrido del corredor
            while (distanciaRecorrida < DISTANCIA_TOTAL) {
                distanciaRecorrida += (int) (Math.random() * INTERVALO);
                Thread.sleep(TIEMPO_ESPERA);
            }

            tiempoLlegada = System.currentTimeMillis();

            // Notifica que ha llegado a la meta
            synchronized (llegada) {
                llegada.notify();
            }

            tiempo = tiempoLlegada - tiempoInicio;
            System.out.printf(
                    "Soy el corredor nº %d y he llegado a la meta en %d milisegundos, y he recorrido %d kilómetros.\n",
                    dorsal, tiempo, distanciaRecorrida);
        } catch (InterruptedException e) {
            // El corredor ha sido interrumpido, muestra un mensaje de perdedor
            tiempoLlegada = System.currentTimeMillis();
            tiempo = tiempoLlegada - tiempoInicio;
            System.out.printf(MSG_PERDEDOR, dorsal, distanciaRecorrida, tiempo);
        }
    }
}
/*
 * Método run(): Este método implementa la lógica principal de un corredor.
 * 
 * Espera la señal de salida: El corredor sincroniza en el objeto salida y
 * espera (salida.wait()) hasta que recibe la señal para empezar la carrera.
 * 
 * Simulación del recorrido: Utiliza un bucle while para simular el recorrido
 * del corredor. En cada iteración, se avanza una distancia aleatoria y se
 * espera un tiempo (Thread.sleep(TIEMPO_ESPERA)) para simular la velocidad del
 * corredor.
 * 
 * Notificación de llegada: Cuando el corredor alcanza la distancia total,
 * notifica a través del objeto llegada que ha llegado a la meta
 * (llegada.notify()).
 * 
 * Cálculo del tiempo: Calcula el tiempo total que tardó en llegar a la meta.
 * 
 * Manejo de interrupciones: Si el corredor es interrumpido (por ejemplo, si la
 * carrera se cancela), muestra un mensaje indicando que es el perdedor y en qué
 * kilómetro se quedó.
 * 
 * Este código simula de manera efectiva una carrera con corredores y utiliza la
 * sincronización con wait() y notifyAll() para coordinar el inicio y
 * finalización de la carrera de manera segura en un entorno multi-hilo.
 */