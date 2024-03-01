package Observer.CodigoPatronObserver.EjercicioTemperaturaObserver;

public class SensorTemperatura implements Runnable {
    private static final long DELAY = 500;
    private static final long MAX_NUM = 150;
    int temperatura = 0;

    public interface InnerSensorTemperatura {
        public void onMessage(String s);
    }

    private InnerSensorTemperatura sensor;

    public void setManejadorMensaje(InnerSensorTemperatura sensorTemp) {
        sensor = sensorTemp;
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
            temperatura = i;

            if (sensor != null) {
                sensor.onMessage("null");
            }
        }
    }

}
