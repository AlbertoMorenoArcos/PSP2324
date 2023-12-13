package ut02.Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Compresor {
    public static void main(String[] args) throws IOException {
        //FORMATEAR FECHA
        String timestamp = ZonedDateTime.now(ZoneId.of("Europe/Madrid")).format(DateTimeFormatter.ofPattern("MM.dd.yyy"));
        String fecha = timestamp.replaceAll("\\.", "_");
        // DIRECCION 1
        String ruta1 = args[0].replaceFirst("\\/", "");
        String rutafinal1 = ruta1.replaceAll("\\/", "_");
        String n_archivo1 = rutafinal1 + "_" + fecha + ".tar.gz";
        // DIRECCION 2
        String ruta2 = args[1].replaceFirst("\\/", "");
        String rutafinal2 = ruta2.replaceAll("\\/", "_");
        String n_archivo2 = rutafinal2 + "_" + fecha + ".tar.gz";
        // DIRECCION 3
        String ruta3 = args[2].replaceFirst("\\/", "");
        String rutafinal3 = ruta3.replaceAll("\\/", "_");
        String n_archivo3 = rutafinal3 + "_" + fecha + ".tar.gz";

        String[] commands = {
                "tar",
                "cvzf",
                n_archivo1,
                args[0]
        };
        String[] commands2 = {
                "tar",
                "cvzf",
                n_archivo2,
                args[1]
        };
        String[] commands3 = {
                "tar",
                "cvzf",
                n_archivo3,
                args[2]
        };
        // creating the process 1
        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.inheritIO();
        // starting the process 1
        Process process1 = pb.start();
        // creating the process 2
        ProcessBuilder pb2 = new ProcessBuilder(commands2);
        pb.inheritIO();
        // starting the process 2
        Process process2 = pb2.start();
        // creating the process 3
        ProcessBuilder pb3 = new ProcessBuilder(commands3);
        pb.inheritIO();
        // starting the process 3
        Process process3 = pb3.start();

        // for reading the output from stream 1
        BufferedReader stdInput1 = new BufferedReader(new InputStreamReader(
                process1.getInputStream()));
        String s = null;
        while ((s = stdInput1.readLine()) != null) {
            System.out.println(s);
        }
        // for reading the output from stream 2 
        BufferedReader stdInput2 = new BufferedReader(new InputStreamReader(
                process2.getInputStream()));
        String s2 = null;
        while ((s2 = stdInput2.readLine()) != null) {
            System.out.println(s2);
        }
        // for reading the output from stream 3
        BufferedReader stdInput3 = new BufferedReader(new InputStreamReader(
                process3.getInputStream()));
        String s3 = null;
        while ((s3 = stdInput3.readLine()) != null) {
            System.out.println(s3);
        }
        
    }
}
