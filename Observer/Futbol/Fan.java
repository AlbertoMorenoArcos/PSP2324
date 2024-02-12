package Observer.Futbol;

public class Fan implements SoccerMatch.Observer {
    String name;

    public Fan(String name) {
        this.name = name;
    }

    @Override
    public void update(String event) {
        System.out.println(String.format("I'm fan %s, something happened: %s.", name, event));
    }
}