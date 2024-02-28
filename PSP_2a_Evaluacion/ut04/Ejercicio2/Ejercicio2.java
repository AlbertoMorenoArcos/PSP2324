package PSP_2a_Evaluacion.ut04.Ejercicio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Ejercicio2 {
    private static final int DEFAULT_PORT = 8765;
    private static final int RESOURCE_POSITION = 1;

    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(DEFAULT_PORT);

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
            // Escribir cabecera
            writer.write("HTTP/1.1 200 OK\n");
            writer.write("Content-Type: application/json; charset=utf-8\n");
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
        String operacion = datos[1].toString();
        int operando1 = Integer.parseInt(datos[2]);
        int operando2 = Integer.parseInt(datos[3]);
        int resultado = 0;
        if (operacion.equals("suma")) {
            resultado = operando1 + operando2;
        } else if (operacion.equals("resta")) {
            resultado = operando1 - operando2;
        } else if (operacion.equals("multiplicacion")) {
            resultado = operando1 * operando2;
        } else if (operacion.equals("division")) {
            resultado = operando1 / operando2;
        }

        return String.format("{\"Resultado\": \"%s\"}", resultado);
    }

    private static String extraeInformacion(String header) {
        return header.split(" ")[RESOURCE_POSITION];
    }
}
