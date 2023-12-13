package Ejercicio5;



public class Principal {
    public static void main(String[] args) {
        Transferencia c = new Transferencia(0);
        Thread bob = new Thread(new RealizadorTransferencias(c));
        Thread alice = new Thread(new RealizadorTransferencias(c));

        bob.start();
        alice.start();

        try {
            bob.join();
            alice.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(String.format("El contenido de la cuenta es: %s", c));
    }
}
