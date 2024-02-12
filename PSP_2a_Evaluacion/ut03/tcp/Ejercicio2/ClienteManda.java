package PSP_2a_Evaluacion.ut03.tcp.Ejercicio2;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteManda {

    public static void main(String[] args) throws InterruptedException {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            String s = "";
            try {
                while (s != null) {
                    s = in.readUTF();
                    System.out.println(s);
                }
            } catch (EOFException e) {
                System.out.println("Se ha cerrado la conexión del servidor");
            }

            in.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}