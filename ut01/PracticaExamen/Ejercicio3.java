/*
 *Realiza un programa Java que reciba como parámetro el nombre de un usuario. 
 El programa Java ejecutará un “ps aux” (comando linux), leerá la información del comando, 
 mostrará la cabecera y todos los procesos de ese usuario que consumen más del 0.0 de memoria.
 */
package ut01.PracticaExamen;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio3 {
    public static void main(String[] args) throws InterruptedException {
        String[] commands = {
                "ps",
                "aux"
        };
        String usuario = args[0];
        try {
            ProcessBuilder pb = new ProcessBuilder(commands);
            Process process = pb.start();
            process.waitFor();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String s;
            System.out.println("USER         PID %CPU %MEM    VSZ   RSS TTY      STAT START   TIME COMMAND");
            while ((s = stdInput.readLine()) != null) {
                String[] partes = s.split("\\s+"); // Divide la línea en partes usando espacios como separadores
                if (partes.length >= 11) {
                    String usuarioProceso = partes[0];
                    String memoria = partes[3];

                    // Comprueba si el usuario es el proporcionado y la memoria es mayor que 0.0
                    if (usuarioProceso.equals(usuario) && Double.parseDouble(memoria) > 0.0) {
                        System.out.println(s);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
