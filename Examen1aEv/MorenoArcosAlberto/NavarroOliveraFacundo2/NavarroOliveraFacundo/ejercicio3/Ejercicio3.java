package ejercicio3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

public class Ejercicio3 {

    private static final int NUMERO_PARAMETROS = 2;
    private static final int CADENA = 0;
    private static String NOMBfICHERO = "ejercicio3/ej3.txt";
    private static String NOMBMD5 = "ejercicio3/md5.txt";
    private static final int MD5 = 1;
    private static final int LUGARDELMD5 = 0;
    private static final String REGEX = " ";

    public static void main(String[] args) {
        // miramos si tenemos los parametros deseados
        if (args.length == NUMERO_PARAMETROS) {
            // recogemos las variables
            String cadena = args[CADENA];
            String md5 = args[MD5];
            // creamos el fichero de la salida donde guardar el resultado del md5
            File salida = new File(NOMBfICHERO);
            // creamos el fichero que le pasaremos al md5
            File fileMD5En = new File(NOMBMD5);
            try {
                // escribimos el md5 pasado en un fichero
                BufferedWriter fw = new BufferedWriter(new FileWriter(fileMD5En));
                fw.write(cadena);
                fw.close();
                // preparamos el comando
                String[] commands = { "md5sum", fileMD5En.getName() };
                // creamos el processBuilder
                ProcessBuilder pb = new ProcessBuilder(commands);
                // redireccionamos la salida al fichero
                pb.redirectOutput(salida);
                // ejecutamos el comando
                pb.start();
                // creamos lo necesario para leer la salida
                FileReader salidaReader = new FileReader(salida);
                BufferedReader bf = new BufferedReader(salidaReader);
                String resultado = "";
                String codigo = "";
                // leemos la salida
                while ((resultado = bf.readLine()) != null) {
                    // sacamos el md5 que hemos guardado en el fichero
                    codigo = resultado.split(REGEX)[LUGARDELMD5];
                    // miramos si se corresponde con el que nos han pasado
                    if (md5.equals(codigo)) {
                        System.out.println("El md5 pasado se corresponde con el de la cadena");
                    } else {
                        System.out.println("El md5 pasado no se corresponde con el de la cadena");
                    }
                }
                bf.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

        }
    }
}