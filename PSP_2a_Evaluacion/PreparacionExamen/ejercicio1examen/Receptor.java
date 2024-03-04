package PSP_2a_Evaluacion.PreparacionExamen.ejercicio1examen;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Receptor {
    public static final int DATABYTE = 1024;
    public static final int NUMERO_PARAMETROS = 2;
    DatagramSocket socket;
    byte[] memoriaRecibo;

    public interface Observer {
        void update(String[] evento, int numeroPuerto);
    }

    private List<Receptor.Observer> observadores;

    public Receptor() {
        observadores = new ArrayList<>();
    }

    public void notificoInfo(String[] evento, int numeroPuertoEnvio) {
        for (Receptor.Observer observer : observadores) {
            observer.update(evento, numeroPuertoEnvio);
        }
    }

    public void addObservador(Observer observer) {
        observadores.add(observer);
    }

    public void envioInformacion(String[] datos, int numeroPuerto) {
        notificoInfo(datos, numeroPuerto);
    }

    public void crearSocket(int puertoEscucha) {
        try {
            socket = new DatagramSocket(puertoEscucha);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        memoriaRecibo = new byte[DATABYTE];
    }

    public void recibirDatos(int puertoEnvio) {

        DatagramPacket paqueteRecibido = new DatagramPacket(memoriaRecibo, memoriaRecibo.length);

        try {
            socket.receive(paqueteRecibido);
            String mensaje = new String(paqueteRecibido.getData(), 0, paqueteRecibido.getLength());
            String datos[] = sacarInfo(mensaje);
            notificoInfo(datos, puertoEnvio);

        } catch (IOException e) {
            System.out.println("IOException capturada. " + e);
        }
    }

    public static String[] sacarInfo(String mensaje) {
        String[] palabras = mensaje.split(" ");
        String[] datos = new String[palabras.length];

        for (int i = 0; i < palabras.length; i++) {
            datos[i] = palabras[i];
        }

        return datos;
    }
}
