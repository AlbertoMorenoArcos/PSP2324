package PSP_2a_Evaluacion.ut03.udp.Ejercicio4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ServerUDP {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        try {
            int puerto = Integer.parseInt(args[0]);
            DatagramSocket socket = new DatagramSocket(puerto); // Abre el socket en el puerto 9876
            byte[] receivedData = new byte[MAX_LENGTH];
            byte[] sendData = new byte[MAX_LENGTH];
            InetAddress address;

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete
                puerto = receivedPacket.getPort();
                address = receivedPacket.getAddress();
                Scanner sc = new Scanner(System.in);
                String messageSend = sc.nextLine();
                sc.close();
                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido de la ip: " + address + " el mensaje: " + message);
                String message1 = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                sendData = message1.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, address, puerto);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}