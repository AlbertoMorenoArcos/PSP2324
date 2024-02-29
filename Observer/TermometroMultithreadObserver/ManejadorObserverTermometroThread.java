package TermometroMultiThread;

import java.util.ArrayList;
import java.util.List;

public class ManejadorObserverTermometroThread implements Runnable {
    private static final int THREADDORMIDO=1000; 
    private static final int MAXGENNUM=100; 
    

    public interface ObservadorThread {

        void actualizar(int temperatura);
    }

    private List<ObservadorThread> observadores = new ArrayList<>();
    private int temperatura;

    public void agregarObservador(ObservadorThread observador) {
        observadores.add(observador);
    }

    public void eliminarObservador(ObservadorThread observador) {
        observadores.remove(observador);
    }

    public void notificarObservadores() {
        for (ObservadorThread observador : observadores) {
            observador.actualizar(temperatura);
        }
    }
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(THREADDORMIDO);
                temperatura=(int)(Math.random()*MAXGENNUM);
                notificarObservadores();
            } catch (InterruptedException e) {
                System.out.println("No ha dormido nada este thread!!");
                e.printStackTrace();
            }
        }
    }
    
}
