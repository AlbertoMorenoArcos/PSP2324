package PSP_2a_Evaluacion.ut03.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDP {
	private static final int MAX_LENGTH = 65535;

	public static void main(String args[]) {
		int puertoServidor = Integer.parseInt(args[0]);
		try {
			DatagramSocket ds = new DatagramSocket(puertoServidor, InetAddress.getByName("127.0.0.1"));
			byte[] buffer = new byte[MAX_LENGTH];

			DatagramPacket p = new DatagramPacket(
					buffer,
					MAX_LENGTH);

			ds.receive(p);
			System.out.println(new String(p.getData(), 0, p.getLength()));

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}