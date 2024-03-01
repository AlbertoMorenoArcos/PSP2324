package Observer.CodigoPatronObserver.EjercicioTemperaturaObserver;

public class DispositivoTemperatura {
    public static void main(String[] args) {
        SensorTemperatura dispositivoConectado = new SensorTemperatura();
        SensorTemperatura dispositivoConectado2 = new SensorTemperatura();

        dispositivoConectado.setManejadorMensaje(
                new SensorTemperatura.InnerSensorTemperatura() {
                    public void onMessage(String s) {
                        System.out.println("Dispositivo conectado 1: La temperatura ha cambiado. "
                                + dispositivoConectado.temperatura);
                    }
                });
        new Thread(dispositivoConectado).start();

        dispositivoConectado2.setManejadorMensaje(
                new SensorTemperatura.InnerSensorTemperatura() {
                    public void onMessage(String s) {
                        System.out.println("Dispositivo conectado 2: La temperatura ha cambiado. "
                                + dispositivoConectado2.temperatura);
                    }
                });
        new Thread(dispositivoConectado2).start();
    }
}

/*
 * SIEMPRE ME PASA QUE SE ME OLVIDA INICIAR EL THREAD!!!
 * new Thread(dispositivoConectado2).start();
 */
