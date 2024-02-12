package PSP_2a_Evaluacion.ut03.tcp.tcpMultithread;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Cliente {

    public static void main(String[] args) throws InterruptedException {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                try {
                    System.out.println("Escribe opcion: \n n -> Numero aleatorio \n salir -> Cerrar conexion");
                    Scanner sc = new Scanner(System.in);
                    String opcion = sc.nextLine();

                    out.writeUTF(opcion);
                    out.flush();
                    int textoRecibido = in.readInt();
                    if (textoRecibido == 404) {
                        System.out.println("Escribe bien la opcion burro.\n");
                    }
                    System.out.println("Numero recibido: " + textoRecibido + "\n");
                } catch (EOFException eof) {
                    break;
                }
            }
            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
