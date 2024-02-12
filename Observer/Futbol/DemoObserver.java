package Observer.Futbol;

public class DemoObserver {
    public static void main(String[] args) {
        System.out.println("Welcome to the soccer match. Enter 'goal' or 'foul' to simulate events.");

        SoccerMatch match = new SoccerMatch();

        Fan fan1 = new Fan("John");
        Fan fan2 = new Fan("Mary");

        match.addObserver(fan1);
        match.addObserver(fan2);

        match.startMatch();
    }
}
