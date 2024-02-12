package PSP_2a_Evaluacion.ut03.tcp.tcpMultithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ServerMultithread {
    public static void main(String[] args) {
        ServerSocket server;
        try {
            server = new ServerSocket(1234);
            while (true) {
                // Espera cliente
                try {
                    Socket socket = server.accept();

                    Thread cliente = new Thread(new ThreadControlClientes(socket));
                    cliente.start();
                } catch (SocketException eof) {
                    System.out.println("Socket cerrado.");
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
