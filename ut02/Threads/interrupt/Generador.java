package interrupt;

public class Generador implements Runnable {

    private static final long TIEMPO_ESPERA = 100;
    int contador;
    boolean seguir;

    public Generador() {
        contador = 0;
        seguir = true;
    }

    public synchronized void parar() {
        seguir = false;
    }

    @Override
    public void run() {
        while (seguir) {
            try {
                Thread.sleep(TIEMPO_ESPERA);
                contador++;
            } catch (InterruptedException e) {
                System.out.println(String.format("He contado %d", contador));
                contador = 0;
            }
        }
    }
}
