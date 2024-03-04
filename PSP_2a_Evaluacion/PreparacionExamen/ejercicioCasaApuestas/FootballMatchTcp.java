package PSP_2a_Evaluacion.PreparacionExamen.ejercicioCasaApuestas;

import java.net.ServerSocket;
import java.util.ArrayList;

class FootballMatchTcp {
    private String team1;
    private String team2;
    private int goalsTeam1;
    private int goalsTeam2;
    private ArrayList<BettingHouseTcp> bettingHouses;

    public FootballMatchTcp(String team1, String team2) {
        this.team1 = team1;
        this.team2 = team2;
        this.goalsTeam1 = 0;
        this.goalsTeam2 = 0;
        this.bettingHouses = new ArrayList<>();
    }

    // Métodos para agregar y eliminar observadores
    public void addObserver(BettingHouseTcp bettingHouse) {
        bettingHouses.add(bettingHouse);
    }

    public void removeObserver(BettingHouseTcp bettingHouse) {
        bettingHouses.remove(bettingHouse);
    }

    // Método para notificar a los observadores sobre los goles marcados
    public void notifyObservers() {
        for (BettingHouseTcp bettingHouse : bettingHouses) {
            bettingHouse.update(this);
        }
    }

    // Método para simular la marcación de un gol
    public void scoreGoal(String team) {
        if (team.equals(team1)) {
            goalsTeam1++;
        } else if (team.equals(team2)) {
            goalsTeam2++;
        }
        notifyObservers();
    }

    // Getters
    public String getTeam1() {
        return team1;
    }

    public String getTeam2() {
        return team2;
    }

    public int getGoalsTeam1() {
        return goalsTeam1;
    }

    public int getGoalsTeam2() {
        return goalsTeam2;
    }
}
