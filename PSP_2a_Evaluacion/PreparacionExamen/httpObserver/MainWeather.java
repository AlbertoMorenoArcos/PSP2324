import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import com.sun.net.httpserver.HttpServer;

public class MainWeather {
    public static void main(String[] args) throws IOException {
        // Create weather monitor
        WeatherMonitor monitor = new WeatherMonitor();

        // Create observers
        WeatherObserverInterface temperatureObserver = new TemperatureObserver();
        WeatherObserverInterface humidityObserver = new HumidityObserver();

        // Register observers
        monitor.addObserver(temperatureObserver);
        monitor.addObserver(humidityObserver);

        // Start monitoring
        monitor.startMonitoring();

        // Create HTTP server
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/weather", new ManejadorHttp(monitor));
        server.setExecutor(Executors.newFixedThreadPool(10)); // Concurrent connections
        server.start();

        System.out.println("HTTP server started at port 8000");
    }
}