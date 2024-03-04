
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class WeatherMonitor {

    private List<WeatherObserverInterface> observers = new ArrayList<>();
    double temperature = 20d;
    double humidity = 50d;
    private final long TEN_SECONDS = 10000l;
    final double MIN_TEMP_CHANGE = -2.0; // Mínima variación de temperatura permitida
    final double MAX_TEMP_CHANGE = 2.0; // Máxima variación de temperatura permitida
    final double MIN_HUM_CHANGE = -5.0; // Mínima variación de temperatura permitida
    final double MAX_HUM_CHANGE = 5.0; // Máxima variación de temperatura permitida
    Random random = new Random();

    public void addObserver(WeatherObserverInterface observer) {
        observers.add(observer);
    }

    public void removeObserver(WeatherObserverInterface observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (WeatherObserverInterface observer : observers) {
            observer.update(temperature, humidity);
        }
    }

    public void startMonitoring() {
        new Thread(() -> {
            while (true) {
                // Generar un número aleatorio en el rango de [-2, 2] y calcular el cambio de
                // temperatura
                double tempChange = MIN_TEMP_CHANGE + (random.nextDouble() * (MAX_TEMP_CHANGE - MIN_TEMP_CHANGE));
                temperature += tempChange; // Actualizar temperatura
                temperature = Math.round(temperature * 100.0) / 100.0; // Redondear a dos decimales

                // HUMIDITY
                double humChange = MIN_HUM_CHANGE + (random.nextDouble() * (MAX_HUM_CHANGE - MIN_HUM_CHANGE));
                humidity += humChange; // Actualizar temperatura
                humidity = Math.round(humidity * 100.0) / 100.0; // Redondear a dos decimales

                // Notify observers
                notifyObservers();

                // Sleep for 10 seconds
                try {
                    Thread.sleep(TEN_SECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}