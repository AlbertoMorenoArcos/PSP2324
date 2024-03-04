package PSP_2a_Evaluacion.PreparacionExamen.ejercicioCasaApuestas;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            ServidorFootballMatchTcp servidor = new ServidorFootballMatchTcp(1234, "Equipo A", "Equipo B");
            servidor.acceptConnections();

            // Simular el marcado de goles en el partido
            FootballMatchTcp match = servidor.getMatch();
            match.scoreGoal("Equipo A");
            match.scoreGoal("Equipo B");
            match.scoreGoal("Equipo A");
            match.scoreGoal("Equipo B");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}