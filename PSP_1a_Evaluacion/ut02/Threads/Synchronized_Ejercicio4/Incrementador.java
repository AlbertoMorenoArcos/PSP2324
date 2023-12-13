package PSP_1a_Evaluacion.ut02.Threads.Synchronized_Ejercicio4;

public class Incrementador implements Runnable {

    Contador cont;

    public Incrementador(Contador c) {
        this.cont = c;
    }

    @Override
    public void run() {
        for (int i = 0; i < Principal.NVECES; i++) {
            cont.incrementa();
        }

    }

}
