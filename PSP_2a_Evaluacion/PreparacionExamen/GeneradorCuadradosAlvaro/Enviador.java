package PSP_2a_Evaluacion.PreparacionExamen.GeneradorCuadradosAlvaro;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import PSP_2a_Evaluacion.PreparacionExamen.GeneradorCuadradosAlvaro.Receptor.CuadradoListener;

public class Enviador implements CuadradoListener {

    private int port;

    Enviador(int port) {
        this.port = port;
    }

    public void cuadradoRecibido(String data) {
        String cuadrado = GeneradorCuadrado.generarCuadrado(data);
        System.out.println(port);
        System.out.println(data);
        try (DatagramSocket socket = new DatagramSocket()) {

            socket.setBroadcast(true);
            byte[] bytes = cuadrado.getBytes();
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length);
            packet.setAddress(InetAddress.getByName("192.168.0.255"));
            packet.setPort(port);
            socket.send(packet);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
