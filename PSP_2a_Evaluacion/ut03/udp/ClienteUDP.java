package PSP_2a_Evaluacion.ut03.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ClienteUDP {

    public static void main(String[] args) {
        String msg = args[0];
        int puertoCliente = Integer.parseInt(args[1]);
        try {
            DatagramSocket ds = new DatagramSocket();
            byte buffer[] = msg.getBytes();
            String ip = "127.0.0.1";
            // ds.setBroadcast(true);
            DatagramPacket p = new DatagramPacket(
                    buffer,
                    buffer.length,
                    InetAddress.getByName(ip),
                    puertoCliente);

            ds.send(p);
            ds.close();
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}