package interrupt;

import java.util.Scanner;

public class Principal {
    private static final Object SALIR = "s";

    public static void main(String[] args) {
        Generador gen = new Generador();
        Thread t = new Thread(gen);

        t.start();

        Scanner sc = new Scanner(System.in);
        String s;
        do {
            s = sc.nextLine();
            t.interrupt();
        } while (!s.equals(SALIR));
        sc.close();
        gen.parar();

        try {
            t.join();
        } catch (InterruptedException e) {
        }
    }
}