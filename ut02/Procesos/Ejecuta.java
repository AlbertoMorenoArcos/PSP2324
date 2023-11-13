package ut02.Procesos;

import java.io.IOException;

public class Ejecuta {
    public static void main(String[] args) throws IOException, InterruptedException{
        ProcessBuilder pb = new ProcessBuilder("xed");

        for (int i = 0; i < args.length; i++){
            pb.start();
        }
        //Process p = pb.start();
        //p.waitFor();
        System.out.println("Se ha terminado el proceso.");
    }
}