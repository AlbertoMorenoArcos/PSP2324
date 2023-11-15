package ut02.Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MonitorMemoria {

    public static void main(String[] args) throws IOException, InterruptedException {
        while (true) {
            try {

                // Ejecuta el comando free -h y obtiene el proceso
                ProcessBuilder processBuilder = new ProcessBuilder("free", "-h");
                Process process = processBuilder.start();
                Thread.sleep(5000);
                // Lee la salida del proceso
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                    reader.lines().forEach(System.out::println);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
