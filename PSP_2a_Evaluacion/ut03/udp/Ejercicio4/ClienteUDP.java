package PSP_2a_Evaluacion.ut03.udp.Ejercicio4;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class ClienteUDP {

    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            byte[] sendData = new byte[MAX_LENGTH];
            byte[] receivedData = new byte[MAX_LENGTH];
            int puerto = Integer.parseInt(args[0]);
            InetAddress ip = InetAddress.getByName(args[1]);
            InetAddress address;
            while (true) {
                Scanner sc = new Scanner(System.in);
                String messageSent = sc.next();

                // Mensaje a enviar
                sendData = messageSent.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, puerto);
                socket.send(sendPacket); // Envía el paquete al servidor

                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete
                address = receivedPacket.getAddress();
                String messageReceived = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido de la ip: " + address + " el mensaje: " + messageReceived);
                // sc.close();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
