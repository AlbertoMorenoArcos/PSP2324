
/* package PracticaExamen.UdpServerPrimo;
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1); // = 65535
    private static final int PORT = 8000;

    public interface Observador {
        void update();
    }

    Observador observador;

    void setObservador(Observador observador) {
        this.observador = observador;
    }

    public static void main(String[] args) {
        int count = 1;
        try {
            DatagramSocket socket = new DatagramSocket();
            InetAddress ipAddress = InetAddress.getByName("localhost"); // Dirección del
            // servidor local
            // InetAddress ipAddress = InetAddress.getByName("192.168.56.101"); // Dirección
            // del servidor máquina virtual

            byte[] sendData = new byte[MAX_LENGTH];
            Observador observadorInstance = () -> {
                System.out.println("Es primo!");
            };
            Client client = new Client();
            client.setObservador(observadorInstance);

            while (true) {
                String sentence = "Dame un número (" + count++ + "º aviso)"; // Mensaje a enviar

                sendData = sentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, PORT);
                socket.send(sendPacket); // Envía el paquete al servidor

                byte[] receivedData = new byte[MAX_LENGTH];

                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println("Número recibido: " + message);
                try {
                    if (isPrime(Integer.parseInt(message))) {
                        observadorInstance.update();
                    }

                } catch (Exception e) {
                    System.out.println("Nan");
                }

                Thread.sleep(1000);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean isPrime(int number) { // qué pasa prim!
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
