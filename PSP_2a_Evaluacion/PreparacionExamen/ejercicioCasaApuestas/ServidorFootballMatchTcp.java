package PSP_2a_Evaluacion.PreparacionExamen.ejercicioCasaApuestas;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

// Clase que representa el servidor del partido de fútbol
class ServidorFootballMatchTcp {
    private FootballMatchTcp match;
    private ServerSocket serverSocket;

    public ServidorFootballMatchTcp(int port, String team1, String team2) throws IOException {
        this.match = new FootballMatchTcp(team1, team2);
        this.serverSocket = new ServerSocket(port);
    }

    // Método para aceptar conexiones de las casas de apuestas
    public void acceptConnections() {
        while (true) {
            try {
                Socket socket = serverSocket.accept();
                System.out.println("Nueva conexión: " + socket);

                // Crear un nuevo hilo para manejar la conexión
                new Thread(new BettingHouseHandler(socket)).start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Clase interna para manejar las conexiones de las casas de apuestas
    private class BettingHouseHandler implements Runnable {
        private Socket socket;

        public BettingHouseHandler(Socket socket) {
            this.socket = socket;
        }

        @Override
        public void run() {
            try {
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println("Mensaje recibido de " + socket + ": " + inputLine);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Método para obtener el partido de fútbol
    public FootballMatchTcp getMatch() {
        return match;
    }
}