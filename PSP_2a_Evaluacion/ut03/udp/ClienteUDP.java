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
<<<<<<< HEAD
            byte buffer[] = "Hola mundo\n".getBytes();
            // String ip = "192.168.20.200";
            String ip = "192.168.20.213";
=======
            byte buffer[] = msg.getBytes();
            String ip = "127.0.0.1";
>>>>>>> e8f6553d57dfc2d7e8bf4d18894bf50945e42c04
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