package PSP_2a_Evaluacion.ut03.udp.Ejercicio6;

import java.util.ArrayList;

public class MensajesRecibidos {
    ArrayList<String> mensajes = new ArrayList<String>();

    public void msgRecibido(String mensaje) {
        mensajes.add(mensaje);
    }

    public void mostrarMensajes() {
        for (String mensaje : mensajes) {
            System.out.println(mensaje);
        }
    }
}
