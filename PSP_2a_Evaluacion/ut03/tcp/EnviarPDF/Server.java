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
			while(true) {
				// Espera cliente
                Socket socket = server.accept();
                
                FileInputStream file_in = new FileInputStream("prueba.pdf");
                byte[] fileBytes = file_in.readAllBytes();
				
				new Thread(()->{
                    try {
                        OutputStream out = socket.getOutputStream();    
                        out.write(fileBytes);
                        socket.close();
                    } catch (Exception e) {}
                    
                }).start();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
