package PSP_2a_Evaluacion.ut03.udp.Ejercicio7;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class PrincipalSimulador {
    public static void main(String[] args) throws UnknownHostException {
        String id = args[0];
        InetAddress ip = InetAddress.getByName(args[1]);
        int puerto = Integer.parseInt(args[2]);
        IAscensor ascensor = new AscensorPaco();
        ascensor.config(id, ip, puerto);
        SimuladorUI simulador = new SimuladorUI();
        Thread threadSimulador = new Thread(new SimuladorUI());
        threadSimulador.start();
        ascensor.run();
        try {
            threadSimulador.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (true) {
            int seleccion = simulador.obtenerSeleccion();

            if (seleccion == 1) {
                ascensor.subir();
            } else if (seleccion == 2) {
                ascensor.bajar();
            } else {
                System.out.println("Opcion no permitida.");
            }
        }

    }
}
