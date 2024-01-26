package PSP_2a_Evaluacion.ut03.udp.EjercicioBroadcastNotepad;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

    public static void main(String[] args) throws UnknownHostException, SocketException {

        DatagramSocket socket = new DatagramSocket();
        InetAddress serverAddress = InetAddress.getByName(args[0]);
        int serverPort = Integer.parseInt(args[1]);

        // Iniciar un nuevo hilo para enviar mensajes al servidor
        Thread responseThread = new Thread(new ResponseHandler(socket, serverAddress, serverPort));
        responseThread.start();

        try {
            responseThread.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
