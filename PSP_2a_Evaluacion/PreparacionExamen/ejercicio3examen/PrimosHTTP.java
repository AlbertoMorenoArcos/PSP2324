package PSP_2a_Evaluacion.PreparacionExamen.ejercicio3examen;

import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class PrimosHTTP {
    private ArrayList<ObservadorMensajes> observadores;
    private ServerSocket server;
    private int numeroInicial;
    private int cantidadPrimos;

    public PrimosHTTP(ServerSocket ss, int nInicial, int cantPrimos) {
        this.server = ss;
        this.numeroInicial = nInicial;
        this.cantidadPrimos = cantPrimos;
        observadores = new ArrayList<ObservadorMensajes>();
    }

    public interface ObservadorMensajes {
        void aviso(String info);
    }

    public void addObservadores(ObservadorMensajes observador) {
        observadores.add(observador);
    }

    public void dispararEvento(String info) {
        for (ObservadorMensajes om : observadores) {
            om.aviso(info);
        }
    }

    public void enviarPrimo() {
        List<Integer> primos = new ArrayList<>();
        int count = 0;
        while (count < cantidadPrimos) {
            if (esPrimo(numeroInicial)) {
                primos.add(numeroInicial);
                count++;
            }
            numeroInicial++;
        }

        for (Integer primo : primos) {
            dispararEvento(String.valueOf(primo) + " Es primo");
        }
    }

    private static boolean esPrimo(int numero) {
        if (numero <= 1) {
            return false; // Los números menores o iguales a 1 no son primos
        }
        for (int i = 2; i * i <= numero; i++) {
            if (numero % i == 0) {
                return false; // El número no es primo
            }
        }
        return true; // El número es primo
    }
}
