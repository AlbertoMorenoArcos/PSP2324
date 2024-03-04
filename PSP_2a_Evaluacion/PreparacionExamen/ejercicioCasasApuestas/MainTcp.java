package PSP_2a_Evaluacion.PreparacionExamen.ejercicioCasasApuestas;

public class MainTcp {

  public static void main(String[] args) {
    // Setting up the football match
    FootbalMatchTcp match = new FootbalMatchTcp();
    match.homeTeam = "Manchester United";
    match.awayTeam = "Liverpool";

    // Registering betting houses as observers
    Bet365Tcp bet365 = new Bet365Tcp("192.168.56.101", 8000);
    BettingHouseTcp williamHillTcp = new WilliamHillTcp("192.168.56.101", 8000);
    match.addObserver(bet365);
    match.addObserver(williamHillTcp);

    // Simulating match events
    try {
      Thread.sleep(5000);
      match.goalScored("Manchester United");
      Thread.sleep(5000);
      match.goalScored("Liverpool");
      Thread.sleep(5000);
      match.goalScored("Manchester United");
      Thread.sleep(5000);
      match.goalScored("Manchester United");
    } catch (Exception e) {
      // TODO: handle exception
    }

  }
}
