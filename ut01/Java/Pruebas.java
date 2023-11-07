package ut01.Java;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.ZonedDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Pruebas {
    public static void main(String[] args) throws IOException {
        String fecha = ZonedDateTime.now(ZoneId.of("Europe/Madrid"))
                .format(DateTimeFormatter.ofPattern("MM.dd.yyy"))
                .replaceAll("\\.", "_");

        for (String arg : args) {
            String ruta = arg.replaceFirst("\\/", "").replaceAll("\\/", "_");
            String n_archivo = ruta + "_" + fecha + ".tar.gz";

            String[] commands = {
                "tar",
                "cvzf",
                n_archivo,
                arg
            };

            ProcessBuilder pb = new ProcessBuilder(commands);
            pb.inheritIO();
            Process process = pb.start();

            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String s;
            while ((s = stdInput.readLine()) != null) {
                System.out.println(s);
            }
        }
    }
}
