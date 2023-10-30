package ut01.Java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Compresor {
    public static void main(String[] args) throws IOException {
        
        String ruta = args[0].replaceFirst("\\/", "");
                String rutafinal = ruta.replaceAll("\\/", "_");
        String timestamp = ZonedDateTime.now(ZoneId.of("Europe/Madrid"))
                .format(DateTimeFormatter.ofPattern("MM.dd.yyy"));
                String fecha = timestamp.replaceAll("\\.", "_");
                String n_archivo = rutafinal + "_" + fecha + ".tar.gz";
         String[] commands = {
                "tar",
                "cvzf",
                n_archivo,
                args[0]
        };
        // creating the process
        ProcessBuilder pb = new ProcessBuilder(commands);
        pb.inheritIO();

        // starting the process
        Process process = pb.start();

        
        // for reading the output from stream
        BufferedReader stdInput = new BufferedReader(new InputStreamReader(
                process.getInputStream()));
        String s = null;
        while ((s = stdInput.readLine()) != null) {
            System.out.println(s);
        }
    }
}
