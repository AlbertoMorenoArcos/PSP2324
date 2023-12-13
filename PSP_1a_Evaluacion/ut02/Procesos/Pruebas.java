package PSP_1a_Evaluacion.ut02.Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Pruebas {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Por favor, proporcione el nombre de un usuario como parámetro.");
            System.exit(1);
        }

        String usuario = args[0];

        try {
            ejecutarComando(usuario);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void ejecutarComando(String usuario) throws IOException, InterruptedException {
        try {
            ProcessBuilder pb = new ProcessBuilder("ps", "aux");
            Process process = pb.start();
            process.waitFor();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            // Lee y muestra la cabecera
            String cabecera = reader.readLine();
            System.out.println(cabecera);

            // Lee y muestra los procesos que cumplen con los criterios
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split("\\s+"); // Divide la línea en partes usando espacios como separadores
                if (partes.length >= 11) {
                    String usuarioProceso = partes[0];
                    String memoria = partes[3];

                    // Comprueba si el usuario es el proporcionado y la memoria es mayor que 0.0
                    if (usuarioProceso.equals(usuario) && Double.parseDouble(memoria) > 0.0) {
                        System.out.println(linea);
                    }
                }
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}