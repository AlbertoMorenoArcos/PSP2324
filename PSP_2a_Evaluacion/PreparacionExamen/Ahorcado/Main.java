package PSP_2a_Evaluacion.PreparacionExamen.Ahorcado;

public class Main {
    public static final int NUMERO_PARAMETROS = 2;

    public static void main(String[] args) {
        String palabra;
        int numeroPuerto;
        boolean palabraCorrecta = false;

        if (args.length != NUMERO_PARAMETROS) {
            System.out.println("Debes ingresar la palabra a adivinar y el numero de puerto.");
            System.out.println("Parametros ingresados: " + args.length);
            System.exit(0);
        }

        palabra = args[0];
        numeroPuerto = Integer.valueOf(args[1]);
        System.out.println("La palabra es de " + palabra.length() + " letras");

        Servidor servidor = new Servidor();
        Cliente cliente = new Cliente();
        servidor.addObservador(cliente);

        servidor.conectoServidor(numeroPuerto);

        do {
            cliente.mandaPalabra();
            palabraCorrecta = servidor.reciboPalabra(palabra);
        } while (!palabraCorrecta);

    }
}
