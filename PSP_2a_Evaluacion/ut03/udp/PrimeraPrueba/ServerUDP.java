package PSP_2a_Evaluacion.ut03.udp.PrimeraPrueba;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class ServerUDP {
	private static final int MAX_LENGTH = 65535;

	public static void main(String args[]) {
		String ip = args[0];
		int puertoServidor = Integer.parseInt(args[1]);
		try {
			DatagramSocket ds = new DatagramSocket(puertoServidor, InetAddress.getByName(ip));
			byte[] buffer = new byte[MAX_LENGTH];

			DatagramPacket p = new DatagramPacket(
					buffer,
					MAX_LENGTH);

			ds.receive(p);
			ds.close();
			System.out.println(new String(p.getData(), 0, p.getLength()));

		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}