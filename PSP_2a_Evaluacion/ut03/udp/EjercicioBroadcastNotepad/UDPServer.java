package PSP_2a_Evaluacion.ut03.udp.EjercicioBroadcastNotepad;

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

                String[] commands = {
                        message
                };

                ProcessBuilder pb = new ProcessBuilder(commands);
                pb.inheritIO();
                pb.start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
