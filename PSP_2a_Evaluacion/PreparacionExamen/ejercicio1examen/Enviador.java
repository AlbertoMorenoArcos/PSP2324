package PSP_2a_Evaluacion.PreparacionExamen.ejercicio1examen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class Enviador implements Receptor.Observer {
    int num1, num2;
    String caracter;
    public static final int DATABYTE = 1024;

    @Override
    public void update(String[] evento, int numeroPuerto) {
        GenerarCuadrado generoCuadrado = new GenerarCuadrado();
        String cuadrado = generoCuadrado.generoCuadrado(evento);
        envioBroadcast(cuadrado, numeroPuerto);
    }

    public void envioBroadcast(String cuadrado, int numeroPuerto) {
        try {
            System.out.println(numeroPuerto);
            DatagramSocket socket = new DatagramSocket();
            socket.setBroadcast(true);
            String ip = "255.255.255.255";
            byte[] memoriaMensaje = new byte[1024];
            memoriaMensaje = cuadrado.getBytes();

            try {
                DatagramPacket paqueteEnvio = new DatagramPacket(memoriaMensaje, memoriaMensaje.length,
                        InetAddress.getByName(ip), numeroPuerto);

                try {
                    socket.send(paqueteEnvio);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (UnknownHostException e) {
                e.printStackTrace();
            }

            socket.close();
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }

}
