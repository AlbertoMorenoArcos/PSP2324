package PSP_2a_Evaluacion.ut03.tcp.Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class ClienteManda {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            System.out.println("Introduce mensaje: ");
            Scanner sc = new Scanner(System.in);
            String texto = sc.nextLine();
            out.writeUTF(texto);

            String s = in.readUTF();
            System.out.println(s);

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}