package PSP_2a_Evaluacion.ut03.tcp.Prueba;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.*;

public class ClienteManda {

    public static void main(String[] args) {
        try {
            Socket socket = new Socket("192.168.20.224", 1234);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("Hola mundo de los sockets!!!\n");
            // String s = in.readLine();
            // System.out.println(s);

            in.close();
            out.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}