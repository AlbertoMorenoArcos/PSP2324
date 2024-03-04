package PracticaExamen.ServerStringInteger;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class StringIntegerClient {

    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1); // = 65535
    private static final int PORT = 8000;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName("localhost"); // Dirección del servidor
            byte[] sendData = new byte[MAX_LENGTH];
            while (true) {

                System.out.println("Insert you message here:");
                String sentence = sc.nextLine(); // Mensaje a enviar

                sendData = sentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, PORT);
                socket.send(sendPacket); // Envía el paquete al servidor

                byte[] receivedData = new byte[MAX_LENGTH];
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido: " + message);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
