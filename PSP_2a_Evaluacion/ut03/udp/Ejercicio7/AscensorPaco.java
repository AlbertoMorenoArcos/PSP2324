package PSP_2a_Evaluacion.ut03.udp.Ejercicio7;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class AscensorPaco implements IAscensor, Runnable {
    private static final long TIEMPO_ESPERA = 1000;
    private String id;
    private InetAddress ip;
    private int puerto;
    private String plantas[] = { "S3", "S2", "S1", "PB", "01", "02", "03" };
    private String planta_ascensor;
    private String direccion = "Parado";

    public void config(String id, InetAddress ip, int puerto) {
        this.id = id;
        this.ip = ip;
        this.puerto = puerto;
        planta_ascensor = plantas[3]; // Por defecto se encuentra en la planta PB, que es el index 3
    }

    public synchronized void subir() {
        Thread t = new Thread(() -> {
            try {
                int indexPlantaActual = obtenerIndicePlanta(planta_ascensor);
                if (indexPlantaActual < plantas.length - 1) {
                    planta_ascensor = plantas[indexPlantaActual + 1];
                    direccion = "U";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.start();

    }

    public synchronized void bajar() {

        Thread t = new Thread(() -> {
            try {
                int indexPlantaActual = obtenerIndicePlanta(planta_ascensor);
                if (indexPlantaActual > 0) {
                    planta_ascensor = plantas[indexPlantaActual - 1];
                    direccion = "D";
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        t.start();

    }

    public synchronized String getPlanta() {
        return planta_ascensor;
    }

    public synchronized String toProtocolo() {
        String protocolo = id + ";" + planta_ascensor + ";" + direccion;
        return protocolo;
    }

    /*
     * public IAscensor fromProtocolo(String info) {
     * return info;
     * }
     */
    public synchronized void run() {
        try {
            DatagramSocket socket = new DatagramSocket();
            String messageSent = toProtocolo() + "\n";
            byte[] sendData = messageSent.getBytes();

            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ip, puerto);
            socket.send(sendPacket);

            Thread.sleep(TIEMPO_ESPERA);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private int obtenerIndicePlanta(String planta) {
        for (int i = 0; i < plantas.length; i++) {
            if (planta.equals(plantas[i])) {
                return i;
            }
        }
        return -1; // Si la planta no se encuentra, podrías manejarlo de alguna manera
    }

}
