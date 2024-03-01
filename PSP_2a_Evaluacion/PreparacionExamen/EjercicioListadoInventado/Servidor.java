package PSP_2a_Evaluacion.PreparacionExamen.EjercicioListadoInventado;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Servidor {
    ServerSocket server;
    String frase;
    int[] numeroLetras;

    // Creo el observer
    public interface Observer {
        void update(int[] evento);
    }

    private List<Servidor.Observer> observadores;

    public Servidor() {
        observadores = new ArrayList<>();
    }

    public void notificoInfo(int[] evento) {
        for (Servidor.Observer observer : observadores) {
            observer.update(evento);
        }
    }

    public void envioInformacion(int[] evento) {
        notificoInfo(evento);
    }

    public void addObservador(Observer observer) {
        observadores.add(observer);
    }

    public void conectoServidor(int puertoEscucha) {
        try {
            server = new ServerSocket(puertoEscucha);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void leerDatos() {
        while (true) {
            try {
                Socket socket = server.accept();
                DataInputStream input = new DataInputStream(socket.getInputStream());
                frase = input.readUTF();

                numeroLetras = contarConsonantesYVocales(frase);

                for (int i = 0; i == numeroLetras.length; i++) {
                    if (i == 0) {
                        System.out.println("Numero de consonantes: " + numeroLetras[i]);
                    } else {
                        System.out.println("Numero de vocales: " + numeroLetras[i]);
                    }
                }

                envioInformacion(numeroLetras);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static int[] contarConsonantesYVocales(String frase) {
        int contadorConsonantes = 0;
        int contadorVocales = 0;

        // Convertimos la frase a minúsculas para simplificar el conteo
        frase = frase.toLowerCase();

        for (int i = 0; i < frase.length(); i++) {
            char caracter = frase.charAt(i);
            if (Character.isLetter(caracter)) {
                if (caracter == 'a' || caracter == 'e' || caracter == 'i' || caracter == 'o' || caracter == 'u') {
                    contadorVocales++;
                } else {
                    contadorConsonantes++;
                }
            }
        }

        int[] resultado = { contadorConsonantes, contadorVocales };
        return resultado;
    }
}
