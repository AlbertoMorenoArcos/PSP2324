package PSP_2a_Evaluacion.PreparacionExamen.TCPbasicoreverse;

import java.io.*;
import java.net.*;

public class TcpServer {
    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Server started. Waiting for client...");

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected.");

            DataInputStream dis = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream dos = new DataOutputStream(clientSocket.getOutputStream());

            String message = dis.readUTF();
            System.out.println("Received message from client: " + message);

            // Reverse the message
            StringBuilder reversedMessage = new StringBuilder(message).reverse();

            dos.writeUTF(reversedMessage.toString());
            System.out.println("Sent reversed message to client: " + reversedMessage);

            dos.close();
            dis.close();
            clientSocket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
