package Observer.Primos;

public class Recibidor implements Runnable {
    public interface MensajeRecibido {
        public void haLlegadoMensaje(String s);
    }

    private static final long DELAY = 1000;

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
                    manejadorInterno.haLlegadoMensaje(Integer.toString(i));
                }
            }

        }
    }

    public boolean esPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
}
