package PSP_2a_Evaluacion.ut03.udp.Ejercicio3;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class ClienteUDP {

    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress IPAddress = InetAddress.getByName("192.168.20.213"); // Dirección del servidor
            byte[] sendData = new byte[MAX_LENGTH];
            byte[] receivedData = new byte[MAX_LENGTH];
            int puerto = Integer.parseInt(args[0]);
            String sentence = args[1];

            // Mensaje a enviar
            sendData = sentence.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, puerto);
            socket.send(sendPacket); // Envía el paquete al servidor

            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            socket.receive(receivedPacket); // Espera y recibe el paquete
            String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            System.out.println("Mensaje recibido el mensaje: " + message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}