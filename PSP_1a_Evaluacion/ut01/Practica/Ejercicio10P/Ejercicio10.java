/*Crea un programa Java que describa las ips que tiene en ordenador en linux */

package PSP_1a_Evaluacion.ut01.Practica.Ejercicio10P;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio10 {

    public static void main(String[] args) throws IOException {
        try {
            String[] commands = {
                    "ifconfig",
                    "-a"
            };
            // creating the process
            ProcessBuilder pb = new ProcessBuilder(commands);
            Process process;
            process = pb.start();
            // for reading the output from stream
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s = null;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
