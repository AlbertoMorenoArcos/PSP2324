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
        ascensor.run();
        simulador.run();

    }
}
