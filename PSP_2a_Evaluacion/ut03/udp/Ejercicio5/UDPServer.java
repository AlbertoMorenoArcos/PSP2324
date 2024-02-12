package PSP_2a_Evaluacion.ut03.udp.Ejercicio5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {

    public static void main(String[] args) {
        try {
            int puerto = Integer.parseInt(args[0]); // IP del puerto donde escucha el servidor
            DatagramSocket socket = new DatagramSocket(puerto);

            // Iniciar un nuevo hilo para manejar los mensajes del cliente
            Thread receiveThread = new Thread(new ReceiveServerHandler(socket));
            receiveThread.start();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ReceiveServerHandler implements Runnable {
    private static final int MAX_LENGTH = 65535;
    private DatagramSocket socket;

    public ReceiveServerHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(new byte[MAX_LENGTH], MAX_LENGTH);
                socket.receive(receivedPacket);

                InetAddress clientAddress = receivedPacket.getAddress(); // IP del cliente que envia el mensaje
                int clientPort = receivedPacket.getPort(); // IP del puerto de envio del cliente

                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido de la IP: " + clientAddress + ", puerto: " + clientPort
                        + ", mensaje: " + message);
                // Iniciar un nuevo hilo para manejar la respuesta al cliente
                Thread responseThread = new Thread(new ResponseHandler(socket, clientAddress, clientPort));
                responseThread.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}