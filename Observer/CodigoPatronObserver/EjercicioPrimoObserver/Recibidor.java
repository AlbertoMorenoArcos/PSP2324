package Observer.CodigoPatronObserver.EjercicioPrimoObserver;

import javax.swing.text.View;

public class Recibidor implements Runnable {

    public interface MensajeRecibido {
        public void onMessage(String s);
    }

    private static final long DELAY = 100;

    private static final double MAX_NUM = 1001;

    private MensajeRecibido manejadorInterno;

    public void setManejadorMensaje(MensajeRecibido manejador) {
        manejadorInterno = manejador;
    }

    @Override
    public void run() {

        while (true) {

            try {
                Thread.sleep(DELAY);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            int i = (int) (Math.random() * MAX_NUM);

            System.out.println(String.format("Generado %d", i));

            if (esPrimo(i)) {
                if (manejadorInterno != null) {
                    manejadorInterno.onMessage("null");
                }
            }
        }

    }

    public static boolean esPrimo(int num) {
        boolean prime = true;
        for (int i = 2; i < num; i++) {
            if (num % i == 0) {
                prime = false;
                break;
            }
        }
        return prime;
    }
}
