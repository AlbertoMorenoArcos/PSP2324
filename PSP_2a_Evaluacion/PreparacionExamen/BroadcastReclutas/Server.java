
/* package PracticaExamen.Reclutas;
 */
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1); // = 65535
    private static final int PORT1 = 8000;
    private static final int PORT2 = 9000;
    static long count = 80_000;

    public static void main(String[] args) {
        System.out.println("Reclutas iniciales: " + count);

        new Thread(() -> {
            try {
                DatagramSocket socket = new DatagramSocket();
                InetAddress ipAddress = InetAddress.getByName("192.168.63.255");
                // todos los que estén en la red 192.168.63.0 pueden recibir mi mensaje

                while (true) {
                    Thread.sleep(20000);
                    byte[] sendData = new byte[MAX_LENGTH];
                    String sentence = "Número actual de reclutas: " + count;

                    sendData = sentence.getBytes();

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, PORT1);
                    socket.send(sendPacket); // Envía el paquete al servidor
                }
            } catch (Exception e) {
            }

        }).start();

        while (true) {
            try {
                DatagramSocket socket = new DatagramSocket(PORT2); // Abre el socket en el puerto 9000
                byte[] receivedData = new byte[MAX_LENGTH];
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket);

                new Thread(() -> {

                    String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                    try {
                        String[] splittedMsg = message.split(" ");
                        switch (splittedMsg[0].toLowerCase()) {
                            case "bajas":
                                count -= Long.parseLong(splittedMsg[1]);
                                break;
                            case "altas":
                                count += Long.parseLong(splittedMsg[1]);
                                break;
                            default:
                                break;
                        }
                    } catch (Exception e) {
                    }

                    // Extrae la información del paquete
                }).start();
            } catch (Exception e) {
                // TODO: handle exception
            }

        }
    }
}
