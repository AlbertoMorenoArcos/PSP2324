package PSP_2a_Evaluacion.PreparacionExamen.GeneradorCuadradosAlvaro;

public class Main {

    public static void main(String[] args) {

        Receptor receptor = new Receptor(Integer.parseInt(args[0]));
        Enviador enviador = new Enviador(Integer.parseInt(args[1]));
        receptor.addListener(enviador);

        receptor.escuchar();
    }

}