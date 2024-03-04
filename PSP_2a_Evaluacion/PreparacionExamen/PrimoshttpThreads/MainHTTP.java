package EjerciciosExamen.LlamadaHTTPThread;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class MainHTTP {
    private static final int RESOURCE_POSITION = 1;

    public static void main(String[] args) {
        int puerto = 8877;
        while (true) {
            try {
                ServerSocket serverSocket = new ServerSocket(puerto);
                Socket connCliente = serverSocket.accept();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                connCliente.getInputStream()));
                String header = reader.readLine();
                System.out.println(header);
                // GET ________ HTTP/1.1
                String info = extraeInformacion(header);
                String[] numeros = info.split("/");
                int n = Integer.parseInt(numeros[1]);
                int m = Integer.parseInt(numeros[2]);
                PrimosHTTP primos = new PrimosHTTP(m, n, serverSocket);
                Logger logeado = new Logger(connCliente, m);
                primos.agregarObservador(logeado);
                new Thread(primos).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}
