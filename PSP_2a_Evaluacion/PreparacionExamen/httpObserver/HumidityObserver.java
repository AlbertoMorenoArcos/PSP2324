package PSP_2a_Evaluacion.PreparacionExamen.httpObserver;

class HumidityObserver implements WeatherObserverInterface {
    @Override
    public void update(double temperature, double humidity) {
        System.out.println("Humidity: " + humidity + "%");
    }
}