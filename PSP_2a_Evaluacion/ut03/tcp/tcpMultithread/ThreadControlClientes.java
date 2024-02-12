package PSP_2a_Evaluacion.ut03.tcp.tcpMultithread;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;

public class ThreadControlClientes implements Runnable {

    private Socket socket;
    private static final int ERROR = 404;

    public ThreadControlClientes(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            String opcionRecibida = "";
            while (true) {

                try {
                    opcionRecibida = in.readUTF();

                    if (opcionRecibida.equals("n")) {
                        int numeroAleatorio = (int) (Math.random() * 10);
                        out.writeInt(numeroAleatorio);
                    } else if (opcionRecibida.equals("salir")) {
                        socket.close();
                    } else if (!opcionRecibida.equals("salir") || !opcionRecibida.equals("n")) {
                        out.writeInt(ERROR);
                    }
                } catch (EOFException eof) {
                    break;
                }

            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
