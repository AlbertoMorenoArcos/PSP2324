package Synchronized_Ejercicio4;

public class Principal {
    public static final int NVECES = 100000;

    public static void main(String[] args) {

        Contador c = new Contador(0);
        Thread t1 = new Thread(new Incrementador(c));
        Thread t2 = new Thread(new Incrementador(c));
        Thread t3 = new Thread(new Incrementador(c));
        Thread t4 = new Thread(new Incrementador(c));
        Thread t5 = new Thread(new Incrementador(c));
        Thread t6 = new Thread(new Decrementador(c));
        Thread t7 = new Thread(new Decrementador(c));
        Thread t8 = new Thread(new Decrementador(c));
        Thread t9 = new Thread(new Decrementador(c));
        Thread t10 = new Thread(new Decrementador(c));

        t1.start();
        t2.start();
        t3.start();
        t4.start();
        t5.start();
        t6.start();
        t7.start();
        t8.start();
        t9.start();
        t10.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
            t5.join();
            t6.join();
            t7.join();
            t8.join();
            t9.join();
            t10.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("El contenido es: %s", c));

    }
}
