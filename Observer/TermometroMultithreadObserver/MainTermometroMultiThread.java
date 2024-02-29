package TermometroMultiThread;

public class MainTermometroMultiThread {
    private static final int THREADDORMIDO=1000; 
    public static void main(String[] args) {
        ManejadorObserverTermometroThread termotetroMultiHilo=new ManejadorObserverTermometroThread();
        ActualizadorTermometroMultiThread observador=new ActualizadorTermometroMultiThread();
        termotetroMultiHilo.agregarObservador(observador);
        Thread hiloTermometro=new Thread(termotetroMultiHilo);
        hiloTermometro.start();
        while (true) {
            try {
                Thread.sleep(THREADDORMIDO);
                System.out.println("Estoy por obra del guion :)");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
