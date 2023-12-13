/*Crea una clase Counter con un método sincronizado increment que incremente una variable count. 
Crea dos hilos que incrementen el contador y observa el resultado. 
Modifica la clase Counter anterior para usar un bloque sincronizado en lugar de un método sincronizado.

Modificación 04a
Modifica el ejercicio para poder incrementar y decrementar, crea 5 hilos que incrementen 1000 veces y 5 que decrementen 1000 veces.
Muestra el resultado de hacer esta operación con sincronización y sin sincornización. */
package PSP_1a_Evaluacion.ut02.Threads.Synchronized_Ejercicio4;

public class Contador {
  private int c = 0;

  public Contador(int c) {
    this.c = c;
  }

  public synchronized void incrementa() {
    c = c + 1;
  }

  public synchronized void decrementa() {
    c = c - 1;
  }

  public String toString() {
    return String.valueOf(c);
  }

}
