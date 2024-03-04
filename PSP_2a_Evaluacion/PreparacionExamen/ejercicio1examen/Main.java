package PSP_2a_Evaluacion.PreparacionExamen.ejercicio1examen;

public class Main {
    public static void main(String[] args) {
        int puertoEscucha = Integer.parseInt(args[0]);
        int puertoEnvio = Integer.parseInt(args[1]);
        Receptor observador = new Receptor();

        Enviador envio = new Enviador();

        observador.addObservador(envio);

        observador.crearSocket(puertoEscucha);

        while (true) {
            observador.recibirDatos(puertoEnvio);
        }

    }
}
