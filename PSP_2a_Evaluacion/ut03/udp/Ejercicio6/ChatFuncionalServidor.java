package PSP_2a_Evaluacion.ut03.udp.Ejercicio6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Scanner;
//TODO mejorar, solo imprime cuando envias

public class ChatFuncionalServidor {
    public static final int NUM_USUARIOS = 2;
    private static final int MAX_LENGTH = 65535;
    private static final int PORT = 9876;

    public static Scanner sc = new Scanner(System.in);

    private static DatagramPacket packageCliente;
    private static ArrayList<String> mensajesCliente = new ArrayList<>();

    public static void main(String[] args) {

        try {
            DatagramSocket socket = new DatagramSocket(PORT); // Abre el socket en el puerto 9876
            System.out.println("Servidor de Eco esperando conexiones en el puerto " + PORT);
            packageCliente = recibir(socket);
            Thread hiloRecibidor = new Thread(() -> {
                while (true) {
                    recibir(socket);
                }
            });

            Thread hiloEnviador = new Thread(() -> {
                while (true) {
                    enviar(socket, packageCliente);
                }
            });

            hiloEnviador.start();
            hiloRecibidor.start();

            // en realidad nunca llegariamos aqui porque esta while ese
            hiloEnviador.join();
            hiloRecibidor.join();
            socket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static DatagramPacket recibir(DatagramSocket socket) {

        try {
            byte[] receivedData = new byte[MAX_LENGTH];

            DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
            socket.receive(receivedPacket); // Espera y recibe el paquete

            // Extrae la información del paquete
            String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
            // el/los mensajes lo guardamos en un array, para cuando tengamos que imprimir
            mensajesCliente.add(message);

            return receivedPacket;

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void enviar(DatagramSocket socket, DatagramPacket receivedPacket) {
        try {
            InetAddress clientAddress = receivedPacket.getAddress();
            int clientPort = receivedPacket.getPort();
            System.out.print("server: ");
            String message = sc.nextLine();// Mensaje a enviar
            // Envia la respuesta de vuelta al cliente
            byte[] sendData = message.getBytes();
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, clientAddress, clientPort);
            socket.send(sendPacket);
            for (String mensaje : mensajesCliente) {
                System.out.println("Cliente: " + mensaje);
            }
            mensajesCliente.clear();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
