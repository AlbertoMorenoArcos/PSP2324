package PSP_2a_Evaluacion.PreparacionExamen.ejercicioCasaApuestas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

class Bet365Tcp implements BettingHouseTcp {
    private Socket socket;
    private PrintWriter out;

    public Bet365Tcp(Socket socket) throws IOException {
        this.socket = socket;
        this.out = new PrintWriter(socket.getOutputStream(), true);
    }

    @Override
    public void update(FootballMatchTcp match) {
        try {
            Thread.sleep(new Random().nextInt(401) + 100); // Simular retraso de 100 a 500 milisegundos
            out.println("Actualización del partido: " + match.getTeam1() + " " + match.getGoalsTeam1() + " - " +
                    match.getGoalsTeam2() + " " + match.getTeam2());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}