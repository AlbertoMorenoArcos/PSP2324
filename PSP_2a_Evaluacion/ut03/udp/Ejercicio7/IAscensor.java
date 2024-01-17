package PSP_2a_Evaluacion.ut03.udp.Ejercicio7;

import java.net.InetAddress;

public interface IAscensor {
    public void config(String id, InetAddress ip, int puerto);

    public void run();

    public void subir();

    public void bajar();

    public String getPlanta();

    public String toProtocolo();

    // IAscensor fromProtocolo(String info);
}
