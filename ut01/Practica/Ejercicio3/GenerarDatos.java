/*Crea un programa que reciba por parámetro un número entero positivo n.
 Este número indicará el número de hijos. 
 Cada hijo generará un fichero con la posibles combinación de caracteres de esa longitud. El hijo 1 una letra, 
el hijo 2 dos letras 'aa' a la 'zz', etc. Los nombres serán datos1.txt, datos2.txt, etc.*/
package ut01.Practica.Ejercicio3;

//import java.io.File;
import java.io.IOException;

public class GenerarDatos {

    public static void main(String[] args) {
        int numeroDatos = Integer.parseInt(args[0]);
        if (numeroDatos > 0) {
            for (int i = 1; i <= numeroDatos; i++) {
                // String n_archivo = "datos" + i + ".txt";
                // tienes que hacer una clase java que reciba un numero y genere las
                // combinaciones de letras de ese numero
                // Y ejecutas java Clase N || echo > archivoNum.txt
                String[] commands = {
                        // comando para ejecutar la clase java
                        "java", "unidad01.entrenamientoC.ejercicio3.EscribirDatos", Integer.toString(i)// ,
                        // redirigir el system.out.print
                        // ">",
                        // nombre del archivo donde va
                        // n_archivo
                };

                ProcessBuilder pb = new ProcessBuilder(commands);
                pb.inheritIO();
                try {
                    Process proceso = pb.start();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        }

    }
}