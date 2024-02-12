package PSP_2a_Evaluacion.ut03.tcp.Ejercicio3;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.Scanner;

public class ClienteManda {
    private static String ARCHIVO = "/home/alberto/PSP2324/PSP_2a_Evaluacion/ut03/tcp/Ejercicio3/pdfCliente.pdf";
    private static int DATABYTE = 1024;

    public static void main(String[] args) throws InterruptedException {

        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            byte[] pdf = new byte[DATABYTE];
            int bytesRead;
            DataInputStream in = new DataInputStream(socket.getInputStream());
            FileOutputStream outputStream = new FileOutputStream(ARCHIVO);

            while ((bytesRead = in.read(pdf)) != -1) {
                outputStream.write(pdf, 0, bytesRead);
            }

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}