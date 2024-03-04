package PSP_2a_Evaluacion.ut03.tcp.EnviarPDF;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Iterator;
import java.io.InputStreamReader;
import java.io.OutputStream;

public class Server {

	public static void main(String[] args) {
		ServerSocket server;

		try {
			server = new ServerSocket(1234);
			while (true) {
				Socket socket = server.accept(); // accept() dentro del while para liberar el socket

				new Thread(() -> {

					try {
						OutputStream outputStream = socket.getOutputStream();
						FileInputStream fileInputStream = new FileInputStream(
								"example.pdf");

						int byteRead;
						while ((byteRead = fileInputStream.read()) != -1) {
							outputStream.write(byteRead);
						}

						fileInputStream.close();
						outputStream.close();
						socket.close();

						System.out.println("File sent successfully.");
					} catch (IOException e) {
						e.printStackTrace();
					}

				}).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
