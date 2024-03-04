
package PSP_2a_Evaluacion.PreparacionExamen.Piramide;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private static final String CHAR = "* "; // IMPORTANTISMO EL ESPACIO DESPUES DEL CARACTER
    private static final String SPACE = " ";
    private static final String LF = "\n";

    public static void main(String[] args) {
        ServerSocket server;
        System.out.println("Server connected.");

        try {
            server = new ServerSocket(1234);
            while (true) {
                Socket socket = server.accept(); // accept() dentro del while para liberar el socket

                new Thread(() -> {

                    try {
                        DataInputStream in = new DataInputStream(socket.getInputStream());
                        String msg = in.readUTF();

                        int num = Integer.parseInt(msg);

                        String response = "";

                        for (int i = 0; i < num; i++) {
                            for (int j = 0; j < num - i - 1; j++) {
                                response += SPACE;
                            }
                            for (int k = 0; k <= i; k++) {
                                response += CHAR;
                            }
                            response += LF;
                        }

                        DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                        out.writeUTF(response);

                        in.close();
                        out.close();
                        socket.close();

                        System.out.println("\tPattern sent to client");
                    } catch (IOException | NumberFormatException e) {
                        e.printStackTrace();
                    }

                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
