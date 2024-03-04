
/* package PracticaExamen.UdpServerPrimo;
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {
    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1); // = 65535
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT); // Abre el socket en el puerto 8000
            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Mensaje recibido: " + message); // Imprimir o hacer lo que sea con el mensaje
                                                                    // recibido
                byte[] sendData = new byte[MAX_LENGTH];

                String num = "" + ((int) (Math.random() * 100));

                sendData = num.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, receivedPacket.getAddress(),
                        receivedPacket.getPort());
                socket.send(sendPacket); // Envía el paquete al servidor

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
