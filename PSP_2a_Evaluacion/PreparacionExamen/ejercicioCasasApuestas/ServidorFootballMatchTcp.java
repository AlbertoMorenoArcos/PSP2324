// package bettinghouses;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorFootballMatchTcp {
    public static void main(String[] args) {

        int port = 0;

        if (args.length != 1) {
            System.out.println("Use : java ServidorFootballMatchTcp <port>");
            return;
        }

        try {
            port = Integer.parseInt(args[0]);

        } catch (Exception e) {
            System.out.println("Invalid port, bye...");
            return;
        }

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server listening at port " + port + "\n\n");

            while (true) {
                Socket clientSocket = serverSocket.accept();

                Thread thread = new Thread(() -> {
                    System.out.print("Client connected: " + clientSocket);
                    String clientName = "";
                    String encodedMessage = "";
                    String messageContent = "";

                    try {
                        DataInputStream inputFromClient = new DataInputStream(clientSocket.getInputStream());

                        encodedMessage = inputFromClient.readUTF();
                    } catch (Exception e) {
                        // TODO: handle exception
                    }

                    clientName = encodedMessage.split(";")[0];
                    messageContent = encodedMessage.split(";")[1];

                    System.out.print(
                            "\nMensaje recibido del cliente " + "-->" + clientName + " : " + messageContent + "\n\n");

                });
                thread.start();

            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
