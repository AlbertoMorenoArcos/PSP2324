package factorial_udp_observer;

import java.net.*;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

public class Client {

    static final String DESC_MSG = "DESC";
    static final int MAX_LENGTH = (int) (Math.pow(2, 14));
    static int usersCounter = 0;
    static boolean isInteger = false;
    static Integer intMessage = null;
    static String messageBack;
    static int port = 8000;
    static String ip = "localhost";
    public static final int ARGS_NUM = 2;
    public static final long ONE_SEC = 1000l;
    static private List<FactorialObserver> observers = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        // Creamos una instancia de observador
        FactorialObserver clientObserver = new ClientObserver();

        // le añadimos a la lista
        observers.add(clientObserver);

        // creamos una instancia de client (sujeto) para que pueda llamar a notify
        // MEJOR sería que implementara la interfaz "Observable", la cual le obligaría a
        // implementar sus metodos (add, remove and notify)
        Client client = new Client();

        try {
            while (true) {
                DatagramSocket socket = new DatagramSocket(); // Abre el socket en el puerto XXXX

                String mensajeEnviar = sc.nextLine();
                // ABRIR SOCKET Y CREAR DATA DE BYTES
                InetAddress ipAddress = InetAddress.getByName(ip); // Dirección del servidor
                byte[] dataEnviar = new byte[MAX_LENGTH];
                dataEnviar = mensajeEnviar.getBytes();

                // ENVIAR
                DatagramPacket sendPacket = new DatagramPacket(dataEnviar, dataEnviar.length, ipAddress, port);
                socket.send(sendPacket); // Envía el paquete al servidor

                // RECIBIR
                byte[] receivedData = new byte[MAX_LENGTH];
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                // Extrae la información del paquete
                String numReceivedString = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                System.out.println(numReceivedString);

                int numReceivedInt = Integer.parseInt(numReceivedString);

                if (esMayorMil(numReceivedInt)) {
                    client.notifyPrimoObservers(numReceivedInt);
                }
                // sc.close(); <-- ESTO JAMAS
                Thread.sleep(ONE_SEC);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static boolean esMayorMil(Integer number) {
        if (number >= 1000) {
            return true;
        } else {
            return false;
        }
    }

    public void addObserver(FactorialObserver observer) {
        observers.add(observer);
    }

    public void removeObserver(FactorialObserver observer) {
        observers.remove(observer);
    }

    private void notifyPrimoObservers(int primo) {
        for (FactorialObserver observer : observers) {
            observer.update(primo);
        }
    }
}
