package PSP_2a_Evaluacion.PreparacionExamen.Ahorcado;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Cliente implements Servidor.Observador {
    public static final int DATABYTE = 1024;

    DatagramSocket socket;
    byte[] memoriaMensaje;
    Scanner in = new Scanner(System.in);

    public void mandaPalabra() {
        try {
            socket = new DatagramSocket();
            memoriaMensaje = new byte[DATABYTE];

            System.out.println("Ingresa una palabra:");
            String palabra = in.nextLine();

            memoriaMensaje = palabra.getBytes();

            try {
                DatagramPacket paqueteEnvio = new DatagramPacket(memoriaMensaje, memoriaMensaje.length,
                        InetAddress.getByName("10.0.2.15"), 3456);

                try {
                    socket.send(paqueteEnvio);
                } catch (IOException e) {
                    System.out.println("Error al enviar el paquete." + e);
                    e.printStackTrace();
                }

            } catch (UnknownHostException e) {
                System.out.println("Error al crear el paquete." + e);
                e.printStackTrace();
            }

        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

    public void cierroConexion() {
        socket.close();
    }

    @Override
    public void update(String valoracion) {
        System.out.println(valoracion);
    }
}
