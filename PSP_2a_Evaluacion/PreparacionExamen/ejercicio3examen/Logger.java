package PSP_2a_Evaluacion.PreparacionExamen.ejercicio3examen;

import java.io.FileWriter;
import java.io.IOException;

import PSP_2a_Evaluacion.PreparacionExamen.ejercicio3examen.PrimosHTTP.ObservadorMensajes;

public class Logger implements ObservadorMensajes {

    @Override
    public void aviso(String info) {
        System.out.println(info);
        FileWriter writer;
        try {
            writer = new FileWriter("primos.txt");
            // Escribir datos en el archivo
            writer.write(info + "\n");

            // Cerrar el FileWriter
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
