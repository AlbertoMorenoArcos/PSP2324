package PSP_2a_Evaluacion.ut03.udp.Ejercicio5;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class UDPClient {

    public static void main(String[] args) throws UnknownHostException, SocketException {

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName(args[0]);
        int serverPort = Integer.parseInt(args[1]);

        // Iniciar un nuevo hilo para enviar mensajes al servidor
        Thread responseThread = new Thread(new ResponseHandler(socket, serverAddress, serverPort));
        responseThread.start();
        // Iniciar un nuevo hilo para recibir mensajes del servidor
        Thread receiveThread = new Thread(new ReceiveClientHandler(socket));
        receiveThread.start();

        try {
            responseThread.join();
            responseThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class ReceiveClientHandler implements Runnable {
    private static final int MAX_LENGTH = 65535;
    private DatagramSocket socket;

    public ReceiveClientHandler(DatagramSocket socket) {
        this.socket = socket;
    }

    public synchronized void enviar() {

    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                byte[] receivedData = new byte[MAX_LENGTH];
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, MAX_LENGTH);
                socket.receive(receivedPacket);

                String messageReceived = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido del servidor: " + messageReceived);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
