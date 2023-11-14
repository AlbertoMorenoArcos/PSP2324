package ut02.Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MonitorMemoria {
    private static final long SEGUNDOS = 5;
    private static final long RETRASO_INICIAL = 0;

    public static void main(String[] args) throws IOException {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);

        // Programa la tarea para ejecutarse cada 5 segundos
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // Ejecuta el comando free -h y obtiene el proceso
                ProcessBuilder processBuilder = new ProcessBuilder("free", "-h");
                Process process = processBuilder.start();

                // Lee la salida del proceso
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    reader.lines().forEach(System.out::println);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, RETRASO_INICIAL, SEGUNDOS, TimeUnit.SECONDS);
    }

}
