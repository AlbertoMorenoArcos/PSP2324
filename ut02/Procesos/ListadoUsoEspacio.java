package ut02.Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListadoUsoEspacio {
    public static void main(String[] args) throws IOException {

        if (args.length == 0) {
            System.out.println("Uso: java ListadoUsoEspacio <directorio1> <directorio2> ...");
            System.exit(1);
        }

        for (int i = 0; i < args.length; i++) {
            String[] commands = {"du", "-h", args[i]};
            try {
                System.out.println("Uso de espacio para el directorio: " + args[i]);

                ProcessBuilder pb = new ProcessBuilder(commands);
                Process process = pb.start();
                process.waitFor();

                BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
                String s = null;
                while ((s = stdInput.readLine()) != null) {
                    System.out.println(s);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
