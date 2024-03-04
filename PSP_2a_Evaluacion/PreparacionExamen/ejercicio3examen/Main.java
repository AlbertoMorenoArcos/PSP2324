package PSP_2a_Evaluacion.PreparacionExamen.ejercicio3examen;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Main {
    private static final int RESOURCE_POSITION = 1;
    private static ServerSocket server;

    public static void main(String[] args) throws IOException {
        int puerto = Integer.parseInt(args[0]);
        server = new ServerSocket(puerto);
        Logger logger = new Logger();

        while (true) {
            Socket connCliente = server.accept();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(
                            connCliente.getInputStream()));
            String header = reader.readLine();
            System.out.println(header);
            // GET ________ HTTP/1.1
            String info = extraeInformacion(header);
            String html = generaPagina(info);

            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(
                            connCliente.getOutputStream()));
            String[] parametros = info.split("/");
            PrimosHTTP primo1 = new PrimosHTTP(server, Integer.parseInt(parametros[1]),
                    Integer.parseInt(parametros[2]));

            primo1.addObservadores(logger);
            primo1.enviarPrimo();
            // Escribir cabecera
            writer.write("HTTP/1.1 200 OK\n");
            writer.write("\n");
            writer.write(html);
            writer.flush();

            reader.close();
            writer.close();
            connCliente.close();

        }
    }

    private static String generaPagina(String info) {
        String[] datos = info.split("/");
        int nInicial = Integer.parseInt(datos[1]);
        int cantidadPrimos = Integer.parseInt(datos[2]);
        StringBuilder stringBuilder = new StringBuilder();

        List<Integer> primos = new ArrayList<>();
        int count = 0;
        while (count < cantidadPrimos) {
            if (esPrimo(nInicial)) {
                primos.add(nInicial);
                count++;
            }
            nInicial++;
        }

        stringBuilder.append("<ul>\n");
        for (Integer primo : primos) {
            stringBuilder.append("<li>" + primo + "</li>");
            stringBuilder.append("\n");
        }
        stringBuilder.append("</ul>\n");
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    private static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false; // Los números menores o iguales a 1 no son primos
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false; // El número no es primo
            }
        }
        return true; // El número es primo
    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}
