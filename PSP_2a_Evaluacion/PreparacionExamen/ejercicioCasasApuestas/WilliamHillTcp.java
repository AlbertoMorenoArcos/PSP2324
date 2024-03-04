s// package bettinghouses;

import java.io.DataOutputStream;
import java.net.Socket;

public class WilliamHillTcp implements BettingHouseTcp {

    private String serverIP;
    private int serverPort;
    private String clientName = "WilliamHill";

    public WilliamHillTcp(String serverIP, int serverPort) {
        this.serverIP = serverIP;
        this.serverPort = serverPort;
    }

    @Override
    public void update(String homeTeam, String awayTeam, int homeGoals, int awayGoals) {
        System.out.printf("\nWILLIAMHILL HAS RECEIVED AN UPDATE : %s %d - %s %d\n", homeTeam, homeGoals,
                awayTeam, awayGoals);
        System.out.println("... Sleeping 2 seconds to simulate delay ...");
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            // TODO: handle exception
        }

        try {
            Socket socket = new Socket(serverIP, serverPort);
            DataOutputStream outToServer = new DataOutputStream(socket.getOutputStream());
            outToServer.writeUTF(
                    String.format("\n%s;IM WILLIAMHILL, NICE GOAL : %s %d - %s %d\n", clientName,
                            homeTeam, homeGoals,
                            awayTeam, awayGoals));
            socket.close();

        } catch (Exception e) {
            System.out.println("Error con socket o algo en WilliamHill");
        }
    }

}
