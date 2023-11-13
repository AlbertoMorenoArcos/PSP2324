package LiftOff;


public class LiftOff implements Runnable {
    private int countDown = 10;
    private static int taskCount = 0;
    private final int id = taskCount;
    
    public LiftOff() {}
    
    public LiftOff(int countDown) {
        this.countDown = countDown;
    }
    
    @Override
    public void run() {
        while (countDown > 0) {
            System.out.println("#" + id + " (" + countDown + ")" );
            countDown--;
        }
        System.out.println("Lanzamiento (" + id + ")");
    }
    
    public static void main(String[] args) {
        LiftOff launch = new LiftOff();
        launch.run();
        LiftOff launch1 = new LiftOff();
        launch1.run();
        LiftOff launch2 = new LiftOff();
        launch2.run();
        LiftOff launch3 = new LiftOff();
        launch3.run();
        System.out.println("Comienza la cuenta atrás!");
    }    
}