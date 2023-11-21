package Ejercicio5;

public class Transferencia {
    private int cuenta = 0;
    private static final int nTransferencias = 100000;
    private static final int dinero = 10;
  
    public Transferencia(int cuenta) {
      this.cuenta = cuenta;
    }
    
    public synchronized void transferir() {
      for(int i = 0; i < nTransferencias; i++){
        cuenta = cuenta + dinero;
      }
    }
    
    public String toString() {
      return String.valueOf(cuenta);
    }
}
