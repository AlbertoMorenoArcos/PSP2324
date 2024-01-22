package PSP_2a_Evaluacion.ut03.udp.ComunicacionUDPBroadcast;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

class ResponseHandler implements Runnable {

    private InetAddress serverAddress;
    private int serverPort;
    private DatagramSocket socket;

    public ResponseHandler(DatagramSocket socket, InetAddress serverAddress, int serverPort) {
        this.socket = socket;
        this.serverAddress = serverAddress;
        this.serverPort = serverPort;
    }

    @Override
    public synchronized void run() {
        try {
            while (true) {
                Scanner sc = new Scanner(System.in);
                String messageSent = sc.nextLine();
                byte[] sendData = messageSent.getBytes();
                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, serverAddress, serverPort);
                socket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}