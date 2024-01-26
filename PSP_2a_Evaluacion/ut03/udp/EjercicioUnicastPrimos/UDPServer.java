package PSP_2a_Evaluacion.ut03.udp.EjercicioUnicastPrimos;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    private static final int MAX_LENGTH = 65535;

    public static void main(String[] args) {

        try {
            int puerto = Integer.parseInt(args[0]);
            DatagramSocket socket = new DatagramSocket(puerto);

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);
                socket.receive(receivedPacket);

                InetAddress clientAddress = receivedPacket.getAddress();
                int clientPort = receivedPacket.getPort();

                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido de la IP: " + clientAddress + ", puerto: " + clientPort
                        + ", mensaje: " + message);
                int numero = Integer.parseInt(message);
                // Respuesta al cliente
                String messageSent = "";
                if (esPrimo(numero) == true) {
                    messageSent = "El número " + numero + " es primo";
                } else {
                    messageSent = "El número " + numero + " no es primo";
                }

                byte[] sendData = messageSent.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress,
                        clientPort);
                socket.send(sendPacket);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false;
        }
        for (int i = 2; i < numero; i++) {
            if (numero % i == 0) {
                return false;
            }
        }
        return true;
    }

}
