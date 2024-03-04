package PSP_2a_Evaluacion.PreparacionExamen.httpObserver;

class TemperatureObserver implements WeatherObserverInterface {

    @Override
    public void update(double temperature, double humidity) {
        System.out.println("Temperature: " + temperature + " Celsius");
    }
}
