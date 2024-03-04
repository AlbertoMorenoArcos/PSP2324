package PSP_2a_Evaluacion.PreparacionExamen.GeneradorCuadradosAlvaro;

public class GeneradorCuadrado {
    public static String generarCuadrado(String data) {

        String[] campos = data.split(" ");

        int altura = Integer.parseInt(campos[0]);
        int anchura = Integer.parseInt(campos[1]);
        char caracter = campos[2].charAt(0);

        StringBuilder resultado = new StringBuilder();

        for (int y = 0; y < altura; y++) {
            for (int x = 0; x < anchura; x++) {
                if (x == 0 || x == (anchura - 1)
                        || y == 0 || y == (altura - 1)) {
                    resultado.append(caracter);
                } else {
                    resultado.append(" ");
                }
            }
            resultado.append("\n");
        }

        return resultado.toString();
    }
}
