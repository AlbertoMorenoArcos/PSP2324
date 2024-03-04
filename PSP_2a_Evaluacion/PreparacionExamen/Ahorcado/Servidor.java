package PSP_2a_Evaluacion.PreparacionExamen.Ahorcado;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    public static final int DATABYTE = 1024;
    public static final String FALLO = "¡¡FALLASTE!!";
    public static final String ACIERTO = "¡¡HAS ADIVINADO LA PALABRA!!";
    public static final int MINIMO_INTENTOS = 0;

    DatagramSocket socket;
    byte[] memoriaMensaje;
    String palabraRecibida;
    int intentos = 5;

    // Creo el observer
    public interface Observador {
        public void update(String valoracion);
    }

    public List<Observador> observer;

    public Servidor() {
        observer = new ArrayList<>();
    }

    public void addObservador(Observador observador) {
        observer.add(observador);
    }

    public void notificoInfo(String valoracion) {
        for (Observador observer : observer) {
            observer.update(valoracion);
        }
    }

    public void conectoServidor(int numeroPuerto) {
        try {
            socket = new DatagramSocket(numeroPuerto);
            memoriaMensaje = new byte[1024];
        } catch (SocketException e) {
            System.out.println("Error al crear el socket. " + e);
        }
    }

    public boolean reciboPalabra(String palabraAdivinar) {
        DatagramPacket paqueteRecibido = new DatagramPacket(memoriaMensaje, memoriaMensaje.length);

        try {
            socket.receive(paqueteRecibido);
            palabraRecibida = new String(paqueteRecibido.getData(), 0,
                    paqueteRecibido.getLength());
        } catch (IOException e) {
            System.out.println("Error al recibir el paquete." + e);
        }

        if (verificoPalabra(palabraAdivinar, palabraRecibida) == true) {
            return true;
        } else {
            intentos--;

            if (intentos == MINIMO_INTENTOS) {
                System.out.println("No te quedan mas intentos. ¡Vuelve a intentarlo!");
                System.exit(0);
            } else {
                System.out.println("Te quedan " + intentos + " intentos.");
            }

            return false;

        }

    }

    public boolean verificoPalabra(String palabraAdivinar, String palabraRecibida) {
        String[] palabraAdivinarArray;
        String[] palabraRecibidaArray;

        if (palabraAdivinar.equals(palabraRecibida)) {
            notificoInfo(ACIERTO);
            return true;
        } else {
            for (int i = 0; i < palabraAdivinar.length(); i++) {
                char letrasAdivinar = palabraAdivinar.charAt(i);

                if (letrasAdivinar == palabraRecibida.charAt(i)) {
                    System.out.println("Letra: " + letrasAdivinar + " en posicion " + (i + 1) +
                            " es correcta.");
                }

            }
            notificoInfo(FALLO);
            return false;
        }
    }

    public void cierroConexion() {
        socket.close();
    }
}
