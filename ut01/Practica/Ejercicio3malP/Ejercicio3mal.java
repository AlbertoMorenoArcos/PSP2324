package ut01.Practica.Ejercicio3malP;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ejercicio3mal {
    public static void main(String[] args) throws IOException {

        int nProcesos = Integer.parseInt(args[0]);
        for (int i = 1; i <= nProcesos; i++) {
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
                    for (int j = 0; j < i; j++) {
                        escritor.write(letra);
                    }
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
