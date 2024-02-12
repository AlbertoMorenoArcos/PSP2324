package PSP_2a_Evaluacion.ut03.tcp.Ejercicio1;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;

public class Server {

    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                Socket socket = server.accept();

                DataInputStream in = new DataInputStream(socket.getInputStream());
                DataOutputStream out = new DataOutputStream(socket.getOutputStream());

                String textoRecibido = in.readUTF();
                System.out.println(textoRecibido);
                int contadorVocales = 0;
                int contadorConsonantes = 0;
                for (int i = 0; i < textoRecibido.length(); i++) {
                    if (textoRecibido.charAt(i) == 'a' || textoRecibido.charAt(i) == 'e'
                            || textoRecibido.charAt(i) == 'i' || textoRecibido.charAt(i) == 'o'
                            || textoRecibido.charAt(i) == 'u') {
                        contadorVocales++;
                    } else {
                        contadorConsonantes++;
                    }
                }

                out.writeUTF("Numero de vocales: " + contadorVocales + "\n" + "Numero de consonantes: "
                        + contadorConsonantes);
                in.close();
                out.close();
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}