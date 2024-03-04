// package bettinghouses;

import java.io.DataOutputStream;
import java.net.Socket;

public class Bet365Tcp implements BettingHouseTcp {

    private String serverIP;
    private int serverPort;
    private String clientName = "Bet365";

    public Bet365Tcp(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    @Override
    public void update(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        System.out.printf("\nNEW GOAL!!! (Update received IN Bet365) : %s %d - %s %d\n", homeTeam, homeGoals, awayTeam,
                awayGoals);
        System.out.println("... Sleeping 1.5 seconds to simulate delay ...");
        try {
            Thread.sleep(1500);

        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            Socket socket = new Socket(serverIP, serverPort);
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            outToServer.writeUTF(
                    String.format("\n%s;NEW GOAL! (Update received from Bet365) : %s %d - %s %d\n", clientName,
                            homeTeam, homeGoals,
                            awayTeam, awayGoals));
            socket.close();

        } catch (Exception e) {
            System.out.println("Error con socket o algo en Bet365");
        }
    }

}
