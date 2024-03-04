
package PSP_2a_Evaluacion.PreparacionExamen.Piramide;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Client {
    private static final int MIN_SIZE = 2;

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Invalid number of parameters (attach only args[0] for the size of the pyramid).");
            return;
        }
        try {
            Integer.parseInt(args[0]);
            if (Integer.parseInt(args[0]) < MIN_SIZE) {
                System.out.println("Pyramid size must be at least " + MIN_SIZE);
            }
        } catch (NumberFormatException e) {
            System.out.println("args[0] must be an integer number.");
            return;
        }

        try {
            Socket socket = new Socket("127.0.0.1", 1234);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            DataInputStream in = new DataInputStream(socket.getInputStream());

            out.writeUTF(args[0]);

            String response = in.readUTF();

            System.out.println(response);

            in.close();
            out.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
