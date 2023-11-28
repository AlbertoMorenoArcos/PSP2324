package CarreraEjercicio4;
public class Corredor implements Runnable {
    int dorsal;
    int distanciaRecorrida;
    Long tiempo = null;
    static final int DISTANCIA_TOTAL = 100, INTERVALO = 10, NUM_CORREDORES = 10;
    static final long TIEMPO_ESPERA = 500;
    private static final String MSG_PERDEDOR = "Soy el perdedor (nº%d) y me he quedado en en kilómetro %d. Tiempo: %d milisegundos.\n";
    Object salida;
    Object llegada;

    public Corredor(int num, Object salida, Object llegada) {
        this.dorsal = num;
        this.salida = salida;
        this.llegada = llegada;
        this.distanciaRecorrida = 0;
    }

    public void run(){
        long tiempoLlegada = 0;
        long tiempoInicio = 0;
        try{
            synchronized (salida) {
                salida.wait();
            }
            tiempoInicio = System.currentTimeMillis();
            while(distanciaRecorrida < DISTANCIA_TOTAL){
                distanciaRecorrida += (int)(Math.random() * INTERVALO);
                Thread.sleep(TIEMPO_ESPERA);
            }
            tiempoLlegada = System.currentTimeMillis();
            synchronized (llegada) {
                llegada.notify();
            }
            tiempo = tiempoLlegada - tiempoInicio;
            System.out.printf("Soy el corredor nº %d y he llegado a la meta en %d milisegundos, y he recorrido %d kilómetros.\n", dorsal, tiempo, distanciaRecorrida);
        } catch (InterruptedException e) {
            tiempoLlegada = System.currentTimeMillis();
            tiempo = tiempoLlegada - tiempoInicio;
            System.out.printf(MSG_PERDEDOR, dorsal, distanciaRecorrida, tiempo);
        }
    }
}