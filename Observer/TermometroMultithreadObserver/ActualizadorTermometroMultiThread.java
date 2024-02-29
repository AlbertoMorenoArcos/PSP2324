package TermometroMultiThread;

import TermometroMultiThread.ManejadorObserverTermometroThread.ObservadorThread;

public class ActualizadorTermometroMultiThread implements ObservadorThread {

    @Override
    public void actualizar(int temperatura) {
        System.out.println("El termometro marca "+temperatura+ "ºC");
    }
}
