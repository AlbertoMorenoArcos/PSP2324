package PSP_2a_Evaluacion.ut03.tcp.Ejercicio3;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

public class Server {

    public static String ARCHIVO = "/home/alberto/PSP2324/PSP_2a_Evaluacion/ut03/tcp/Ejercicio3/DNI_Alberto.pdf";
    private static int DATABYTE = 1024;

    public static void main(String[] args) throws InterruptedException {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();

                DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                File archivopdf = new File(ARCHIVO);
                FileInputStream fileInputStream = new FileInputStream(archivopdf);

                byte[] pdf = new byte[DATABYTE];
                int bytesRead;

                while ((bytesRead = fileInputStream.read()) != -1) {
                    out.write(pdf, 0, bytesRead);
                }

                out.close();
                fileInputStream.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}