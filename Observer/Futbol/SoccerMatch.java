package Observer.Futbol;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class SoccerMatch {

    public interface Observer {
        void update(String event);
    }

    private final List<Observer> observers = new ArrayList<>();

    private void notifyObservers(String event) {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(event);
        }

        // EXAMPLE WITH FOREACH

        /*
         * for (Observer observer : observers) {
         * observer.update(event);
         * }
         */
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void goalScored(String team) {
        String event = String.format("Goal scored for team %s!", team);
        notifyObservers(event);
    }

    public void foulCommitted(String player) {
        String event = String.format("Foul committed by %s!", player);
        notifyObservers(event);
    }

    public void startMatch() {
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if (line.equalsIgnoreCase("goal")) {
                System.out.print("Which team scored the goal? ");
                String team = sc.nextLine();
                goalScored(team);
            } else if (line.equalsIgnoreCase("foul")) {
                System.out.print("Who committed the foul? ");
                String player = sc.nextLine();
                foulCommitted(player);
            } else {
                System.out.println("Unrecognized event.");
            }
        }
    }
}
