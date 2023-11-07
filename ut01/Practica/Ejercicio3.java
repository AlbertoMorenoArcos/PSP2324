package ut01.Practica;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio3 {
    public static void main(String[] args) throws IOException {

        int nProcesos = Integer.parseInt(args[0]);
        for (int i = 0; i < nProcesos; i++) {
            String n_archivo = "datos" + i + ".txt";
            String[] commands = {
                    "echo",
                    ">",
                    n_archivo
            };

            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.inheritIO();
            Process process = pb.start();
            try (BufferedWriter escritor = new BufferedWriter(new FileWriter(n_archivo))) {
                // Escribir el abecedario en el archivo
                for (char letra = 'a'; letra <= 'z'; letra++) {
                    escritor.write(letra);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        }
    }

}
