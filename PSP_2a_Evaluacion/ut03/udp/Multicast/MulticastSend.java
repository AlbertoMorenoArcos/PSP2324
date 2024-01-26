package PSP_2a_Evaluacion.ut03.udp.Multicast;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.MulticastSocket;
import java.net.NetworkInterface;
import java.util.Collections;
import java.util.Enumeration;
import java.util.Scanner;

public class MulticastSend {

    public static void main(String[] args) {
        try {

            Enumeration<NetworkInterface> nets = NetworkInterface.getNetworkInterfaces();
            for (NetworkInterface netint : Collections.list(nets)) {
                System.out.println(netint);
            }
            Scanner in = new Scanner(System.in);
            System.out.println("Especifica el nombre del interfaz");
            String iName = in.nextLine();
            NetworkInterface netIf = NetworkInterface.getByName(iName);
            System.out.println(netIf);

            String msg = "Hola soy alberto";
            int port = 1234;

            InetAddress mcastaddr = InetAddress.getByName("230.0.0.1");
            InetSocketAddress group = new InetSocketAddress(mcastaddr, port);
            MulticastSocket s = new MulticastSocket(port);

            s.joinGroup(group, netIf);

            byte[] msgBytes = msg.getBytes();
            DatagramPacket hi = new DatagramPacket(msgBytes, msgBytes.length, group);
            s.send(hi);

            /* Código de envío */

            // Lo dejo cuando quiera
            s.leaveGroup(group, netIf);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
