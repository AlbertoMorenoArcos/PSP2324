package PSP_1a_Evaluacion.PSP1aEvTeoria.EjemploSingleton;

public class Main {

    public static void main(String[] args) {

        SoyUnico ricardo = SoyUnico.getSingletonInstance("Ricardo Moya");
        SoyUnico ramon = SoyUnico.getSingletonInstance("Ramón Invarato");

        // ricardo y ramon son referencias a un único objeto de la clase SoyUnico
        System.out.println(ramon.getNombre());
        System.out.println(ricardo.getNombre());

        // No se permite clonar un objeto de esta clase
        try {
            SoyUnico richard = ricardo.clone();
            System.out.println(richard.getNombre());
        } catch (NullPointerException ex) {
            ex.printStackTrace();
        }

    }

}