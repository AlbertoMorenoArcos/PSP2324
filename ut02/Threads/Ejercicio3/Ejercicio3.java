/*Un elefante se balanceaba sobre la tela de una araña
Como veía que resistía, fue a llamar otro elefante
Dos elefantes se balanceaban sobre la tela de una araña
Como veían que resistía, fueron a llamar otro elefante

Basándote en esa canción, crea un Thread que reciba el tipo de animal, la acción y el número máximo. 
Cada vez que escriba una estrofa, el thread generará un número aleatorio entre 100000 y 300000 y verificará si es primo.

Crea un programa principal que gestion 3 canciones infantiles de forma concurrente con distintas prioridades (setPriority) */

package Ejercicio3;

import java.util.Random;

public class Ejercicio3 {

    public static void main(String[] args) {
        animales perro = new animales("perro", "ladraba", 8);
        animales gato = new animales("gato", "maullaba", 4);
        animales raton = new animales("raton", "mordisqueaba", 5);
        
        Thread threadPerro = new Thread(perro);
        Thread threadGato = new Thread(gato);
        Thread threadRaton = new Thread(raton);
        
        threadPerro.setPriority(Thread.NORM_PRIORITY);
        threadGato.setPriority(Thread.MAX_PRIORITY);
        threadRaton.setPriority(Thread.MIN_PRIORITY);

        threadPerro.start();
        threadGato.start();
        threadRaton.start();
    }
}

class animales extends Thread {
    private String nombreAnimal;
    private String accion;
    private int numMaximo;

    public animales(String nombreAnimal, String accion, int numMaximo) {
        this.nombreAnimal = nombreAnimal;
        this.accion = accion;
        this.numMaximo = numMaximo;
    }

    public void run() {

        for (int i = 1; i <= numMaximo; i++) {
            int numeroAleatorio = numeroAleatorio(100000, 300000);

            System.out.println(i + " " + nombreAnimal + " se " + accion + " sobre la tela de una araña\n" +
                    "Como veían que resistía, fueron a llamar otro " + nombreAnimal);

            if (esPrimo(numeroAleatorio) == true) {
                System.out.println("El numero aleatorio " + numeroAleatorio + " SI es primo.");
            } else {
                System.out.println("El numero aleatorio " + numeroAleatorio + " NO es primo.");
            }

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.getMessage();
            }

        }

    }

    public int numeroAleatorio(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    public boolean esPrimo (int numero) {
        for (int i = 2; i <= Math.sqrt(numero); i++) {
            if (numero % i == 0) {
                return false; 
            }
        }
    
        return true;
    }

}
