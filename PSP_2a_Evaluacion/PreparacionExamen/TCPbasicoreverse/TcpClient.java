import java.io.*;
import java.net.*;

public class TcpClient {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("localhost", 12345);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            String message = "Hello, Server!";
            System.out.println("Sending message to server: " + message);
            dos.writeUTF(message);

            String reversedMessage = dis.readUTF();
            System.out.println("Received reversed message from server: " + reversedMessage);

            dos.close();
            dis.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
