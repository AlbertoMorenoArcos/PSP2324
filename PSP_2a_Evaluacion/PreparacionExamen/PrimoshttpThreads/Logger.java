package EjerciciosExamen.LlamadaHTTPThread;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import EjerciciosExamen.LlamadaHTTPThread.PrimosHTTP.ObservadorHTTPThread;

public class Logger implements ObservadorHTTPThread{

    private List<Integer> datosRecibidos = new ArrayList<>();
    private Socket connCliente;
    private int maximo;


    public Logger(Socket connCliente,int maximo) {
        this.connCliente = connCliente;
        this.maximo=maximo;
    }

    @Override
    public void actualizar(int numero, int contador) {
        if (contador < maximo-1) {
            datosRecibidos.add(numero);
        } else {
            datosRecibidos.add(numero);
            String paginaWeb = crearPaginaWeb(datosRecibidos);
            try {
                // Get the output stream from the socket
                OutputStream out = connCliente.getOutputStream();

                // Send the HTTP response header
                String response = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/html; charset=UTF-8\r\n" +
                        "Content-Length: " + paginaWeb.length() + "\r\n\r\n";
                out.write(response.getBytes());
                // Send the HTML content
                out.write(paginaWeb.getBytes());
                out.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String crearPaginaWeb(List<Integer> datosRecibidos) {
        StringBuilder html = new StringBuilder();
        html.append("<!DOCTYPE html>");
        html.append("<html>");
        html.append("<head>");
        html.append("<title>Números Primos</title>");
        html.append("</head>");
        html.append("<body>");
        html.append("<h1>Números Primos</h1>");
        html.append("<ul>");
        for (int primo : datosRecibidos) {
            html.append("<li>" + primo + "</li>");
        }
        html.append("</ul>");
        html.append("</body>");
        html.append("</html>");
        return html.toString();
    }
}
