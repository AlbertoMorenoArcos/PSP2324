package Carrera;

public class Corredor  implements Runnable{
    private static final long TIEMPO_DESCANSO = 500;
    private static final double MAX_INTERVALO_KM = 10;
    int kmRecorrido;
    int kmTotales;
    int dorsal;
    public Object run;
    public Corredor(int kmTotales, int dorsal){
        this.kmTotales = kmTotales;
        this.dorsal= dorsal;
        this.kmRecorrido = 0;
    }
    public void run(){
        System.out.println(String.format("Soy el dorsal %d inicio mi carrera", dorsal));
        while(kmRecorrido<kmTotales){
            try {
                long descanso = (long) (Math.random()*TIEMPO_DESCANSO+TIEMPO_DESCANSO);
                Thread.sleep(descanso);
                System.out.println(String.format("Soy el dorsal %d he descansado: %d", dorsal, descanso));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            kmRecorrido += Math.random()*MAX_INTERVALO_KM;
            System.out.println(String.format("Soy el dorsal %d he recorrido: %d de: %d!!", dorsal, kmRecorrido,kmTotales));
        }
        System.out.println(String.format("Soy el dorsal %d Termine!!", dorsal));
    }
}
