import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;

public class Cliente {
    public static void main(String[] args) {
        try {
            Socket socket = new Socket("127.0.0.1", 1234);

            InputStream in = socket.getInputStream();
            FileOutputStream fileOut = new FileOutputStream("cliente_prueba.pdf");

            int currByte;
            while((currByte = in.read()) != -1) {
                fileOut.write(currByte);
            }
            socket.close();
        } catch (Exception e) {
            //TODO: handle exception
        }
    }
}
