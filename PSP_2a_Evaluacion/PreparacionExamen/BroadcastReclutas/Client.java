
package PSP_2a_Evaluacion.PreparacionExamen.BroadcastReclutas;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Client {
    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1); // = 65535
    private static final int PORT1 = 8000;
    private static final int PORT2 = 9000;

    public static void main(String[] args) {

        // Thread para recibir el broadcast
        new Thread(() -> {
            try {
                DatagramSocket socket = new DatagramSocket(PORT1); // Abre el socket en el puerto 8000
                byte[] receivedData = new byte[MAX_LENGTH];
                socket.setBroadcast(true);

                while (true) {
                    Thread.sleep(6000);
                    DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                    socket.receive(receivedPacket); // Espera y recibe el paquete

                    // Extrae la información del paquete
                    String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                    System.out.println(message); // Imprimir o hacer lo que sea con el mensaje
                                                 // recibido
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        Scanner sc = new Scanner(System.in);
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName("localhost"); // Dirección del servidor
            byte[] sendData = new byte[MAX_LENGTH];

            while (true) {
                System.out.println("Actualiza la info sobre altas y bajas:");
                String sentence = sc.nextLine();

                sendData = sentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, PORT2);
                socket.send(sendPacket); // Envía el paquete al servidor
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
