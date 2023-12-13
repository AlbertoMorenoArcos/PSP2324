package PSP_1a_Evaluacion.ut02.Procesos;

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
                // Lee la salida del proceso
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s = null;
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                }
                Thread.sleep(5000);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
