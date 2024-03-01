package PSP_2a_Evaluacion.PreparacionExamen.EjercicioListadoInventado;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {
    private static final int NUMERO_PARAMETROS = 2;

    public static void main(String[] args) {
        int numeroPuerto;
        String mensaje;

        if (args.length != NUMERO_PARAMETROS) {
            System.out.println("Debes ingresar el numero de puerto y un mensaje.");
            System.exit(0);
        }

        numeroPuerto = Integer.parseInt(args[0]);
        mensaje = args[1];

        // instancio dos objetos de las clases
        Servidor observador = new Servidor();
        Cliente cliente = new Cliente();
        Cliente cliente2 = new Cliente();

        // Añado el cliente al observer
        observador.addObservador(cliente);
        cliente.setNombre();
        observador.addObservador(cliente2);
        cliente2.setNombre();

        // Pongo el servidor a escuchar
        observador.conectoServidor(numeroPuerto);

        try {
            cliente.envioFrase(numeroPuerto, InetAddress.getByName("10.0.2.15"), mensaje);
            cliente2.envioFrase(numeroPuerto, InetAddress.getByName("10.0.2.15"), "hola que tal cuentame esta.");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        while (true) {
            observador.leerDatos();
        }
    }

}