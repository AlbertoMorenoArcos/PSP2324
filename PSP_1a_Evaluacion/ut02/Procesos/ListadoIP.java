package ut02.Procesos;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ListadoIP {
    public static void main(String[] args) throws InterruptedException {
        String sistemaOperativo = System.getProperty("os.name").toLowerCase();

        if (sistemaOperativo.contains("win")) {
            listarIPsWindows();
        } else if (sistemaOperativo.contains("nix") || sistemaOperativo.contains("nux")
                || sistemaOperativo.contains("mac")) {
            listarIPsLinux();
        } else {
            System.out.println("Sistema operativo no soportado.");
        }
    }

    public static void listarIPsWindows() throws InterruptedException {
        ejecutarComando("ipconfig");
    }

    public static void listarIPsLinux() throws InterruptedException {
        ejecutarComando("ifconfig");
    }

    public static void ejecutarComando(String commands) throws InterruptedException {
        try {
            ProcessBuilder pb = new ProcessBuilder(commands);
            Process process = pb.start();
            process.waitFor();
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String s;
            System.out.println("Direcciones IP encontradas:");

            while ((s = stdInput.readLine()) != null) {
                if (s.matches(".*\\d+\\.\\d+\\.\\d+\\.\\d+.*")) {
                    System.out.println(s.trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
