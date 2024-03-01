package Observer.CodigoPatronObserver.EjercicioPrimoObserver;

public class Tester {
    public static void main(String[] args) {
        Recibidor recibidorUDP = new Recibidor();

        recibidorUDP.setManejadorMensaje(
                new Recibidor.MensajeRecibido() {

                    public void onMessage(String s) {
                        System.out.println("PRIMO!");
                    }
                });
        new Thread(recibidorUDP).start();
    }
}
