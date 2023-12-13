package Ejercicio5;

public class RealizadorTransferencias implements Runnable {

    Transferencia tran;

    public RealizadorTransferencias(Transferencia c) {
        this.tran = c;
    }

    @Override
    public void run() {
        tran.transferir();
    }

}
