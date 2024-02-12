package PSP_2a_Evaluacion.ut03.tcp.Ejercicio3;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class prueba {
    public static String ARCHIVO = "/home/alberto/PSP2324/PSP_2a_Evaluacion/ut03/tcp/Ejercicio3/pdfServidor.txt";

    public static void main(String[] args) throws IOException {

        try {

            try {
                BufferedReader entrada = new BufferedReader(new FileReader(ARCHIVO));
                String textoPDF;
                while ((textoPDF = entrada.readLine()) != null) {
                    System.out.println(textoPDF);
                }

            } catch (FileNotFoundException e) {
                System.out.println("No se ha encontrado el archivo.");
            }
        } catch (FileNotFoundException e) {
            System.out.println("No se ha encontrado el archivo.");
        }
    }

}
