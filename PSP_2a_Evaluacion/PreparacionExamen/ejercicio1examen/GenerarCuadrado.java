package PSP_2a_Evaluacion.PreparacionExamen.ejercicio1examen;

public class GenerarCuadrado {

    public String generoCuadrado(String[] datos) {
        int alto = Integer.parseInt(datos[0]);
        int ancho = Integer.parseInt(datos[1]);
        char caracter = datos[2].charAt(0);

        StringBuilder cuadrado = new StringBuilder();
        for (int i = 0; i < alto; i++) {
            for (int j = 0; j < ancho; j++) {
                if (i == 0 || i == alto - 1 || j == 0 || j == ancho - 1) {
                    cuadrado.append(caracter);
                } else {
                    cuadrado.append(" ");
                }
            }
            cuadrado.append("\n");
        }
        return cuadrado.toString();
    }

}
