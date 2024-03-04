package PSP_2a_Evaluacion.PreparacionExamen.factorial;

public class ClientObserver implements FactorialObserver {

    @Override
    public void update(int n) {
        System.out.printf("\nEl factorial es mayor a 1000 : %d\n", n);
    }

}
