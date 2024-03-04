# EnunciadosPSP

## MARÍA - MONITOREO DE TEMPERATURA Y HUMEDAD CON OBSERVER + HTTP SERVER

**Examen: Implementación de Monitoreo de Clima en Tiempo Real**

**Instrucciones:**

1. Desarrolle un sistema de monitoreo de temperatura y humedad en tiempo real utilizando el patrón de diseño Observer en Java.
2. Cree una clase `WeatherMonitor` que actúe como el sujeto observable. Esta clase debe ser capaz de registrar observadores y notificarlos cuando se produzcan cambios en la temperatura y la humedad.
3. Implemente una interfaz `WeatherObserver` que contenga un método `update` para que los observadores reciban actualizaciones del clima.
4. Cree al menos dos clases de observadores concretos que implementen la interfaz `WeatherObserver`. Por ejemplo, puede tener un observador para registrar los cambios en la temperatura y otro para registrar los cambios en la humedad.
5. Desarrolle un servidor HTTP en Java utilizando la API `HttpServer` de Java para permitir que los clientes obtengan información de monitoreo del clima a través de solicitudes HTTP.
6. El servidor HTTP debe proporcionar al menos una ruta `/weather` que devuelva los datos de monitoreo del clima en formato JSON. Este formato debe incluir información como la temperatura actual y la humedad.
7. Implemente la lógica necesaria para actualizar los datos de monitoreo del clima en tiempo real y notificar a los observadores registrados cuando se produzcan cambios.
8. Asegúrese de que el servidor HTTP maneje correctamente las solicitudes entrantes y responda con los datos de monitoreo actualizados.
9. Proporcione comentarios detallados en su código para explicar la estructura del programa y cómo se implementa el patrón Observer y el servidor HTTP.
10. Verifique que el código esté bien estructurado, siga las mejores prácticas de codificación y maneje adecuadamente los errores y excepciones.
