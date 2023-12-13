package PSP_1a_Evaluacion.Examen1aEv.NavarroOliveraFacundo2.NavarroOliveraFacundo.ejercicio4;

public class Ejercicio4 {

    private static final int NUMERO_PARAMETROS = 1;
    private static final int DISTANCIA_A_RECORRER = 408;
    private static final int LUGAR_COHETE = 0;
    private static final Integer EXITO = 3;
    private static String FRASE_CORRECTO = "La mision ha sido un exito";
    private static String FRASE_FALLO = "La mision ha sido un fracaso";

    public static void main(String[] args) {
        // miramos si tenemos los parametros correctos
        if (args.length == NUMERO_PARAMETROS) {
            // sacamos los cohetes a lanzar
            int numeroCohetes = Integer.parseInt(args[LUGAR_COHETE]);
            // creamos al array de cohetes
            Thread cohetes[] = new Thread[numeroCohetes];
            // creamos los cohetes con el punto final de su travesia
            for (int i = 0; i < numeroCohetes; i++) {
                cohetes[i] = new Thread(new Cohete(DISTANCIA_A_RECORRER));
            }
            // arrancan los cohetes
            for (int i = 0; i < numeroCohetes; i++) {
                cohetes[i].start();
            }
            // esperamos a los cohetes
            for (int i = 0; i < numeroCohetes; i++) {
                try {
                    cohetes[i].join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            // miramos como ha ido la mision
            if (Cohete.exito >= EXITO) {
                System.out.println(FRASE_CORRECTO);
                System.out.printf("Han llegado %d cohetes de %d\n", Cohete.exito, numeroCohetes);
            } else {
                System.out.println(FRASE_FALLO);
                System.out.printf("Han explotado %d cohetes de %d\n", numeroCohetes - Cohete.exito, numeroCohetes);

            }
        }
    }

}
