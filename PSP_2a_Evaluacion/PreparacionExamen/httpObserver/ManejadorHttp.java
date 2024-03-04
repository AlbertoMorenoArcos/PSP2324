package PSP_2a_Evaluacion.PreparacionExamen.httpObserver;

import java.io.IOException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ManejadorHttp implements HttpHandler {
    private WeatherMonitor monitor;

    public ManejadorHttp(WeatherMonitor monitor) {
        this.monitor = monitor;
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "{\"temperature\": " + monitor.temperature + ", \"humidity\": " + monitor.humidity + "}";
        exchange.sendResponseHeaders(200, response.getBytes().length);
        exchange.getResponseBody().write(response.getBytes());
        exchange.close();
    }
}