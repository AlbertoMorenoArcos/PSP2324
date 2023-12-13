package Synchronized_Ejercicio4;

public class Decrementador implements Runnable{

    Contador cont;

    public Decrementador (Contador c){
        this.cont = c;
    }

    @Override
    public void run() {
        for(int i = 0; i< Principal.NVECES; i++){
            cont.decrementa();
        }
   
    }
    
}
