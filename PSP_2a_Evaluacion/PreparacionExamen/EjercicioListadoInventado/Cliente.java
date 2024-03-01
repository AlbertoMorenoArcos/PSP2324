package PSP_2a_Evaluacion.PreparacionExamen.EjercicioListadoInventado;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class Cliente implements Servidor.Observer {
    Socket socket;
    String nombre;
    Scanner in = new Scanner(System.in);

    @Override
    public void update(int[] evento) {
        System.out.println("Info recibida por cliente " + getNombre() + ":\nNumero de vocales: " + evento[0]);
        System.out.println("Numero de consonantes: " + evento[1]);
    }

    public void setNombre() {
        System.out.println("Ingresa un nombre:");
        nombre = in.nextLine();
    }

    public String getNombre() {
        return nombre;
    }

    public void envioFrase(int puertoEnvio, InetAddress direccionIp, String mensaje) {
        try {
            socket = new Socket(direccionIp, puertoEnvio);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(mensaje);
        } catch (IOException e) {
            System.out.println("Error al crear el socket.");
            e.printStackTrace();
        }

        try {
            socket.close();
        } catch (IOException e) {
            System.out.println("Error al cerrar el socket.");
            e.printStackTrace();
        }
    }
}