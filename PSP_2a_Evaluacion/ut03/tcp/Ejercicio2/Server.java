package PSP_2a_Evaluacion.ut03.tcp.Ejercicio2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

public class Server {

    public static void main(String[] args) throws InterruptedException {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                System.out.println("Ficheros: ");
                String[] commands = {
                        "ls",
                        "/var/"
                };
                // creating the process 1
                ProcessBuilder pb = new ProcessBuilder(commands);
                Process proceso = pb.start();
                BufferedReader stdInput = new BufferedReader(new InputStreamReader(proceso.getInputStream()));

                String directorios;

                while ((directorios = stdInput.readLine()) != null) {
                    System.out.println("Lista de ficheros: " + directorios);
                    out.writeUTF(directorios);

                }
                out.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}