package PSP_2a_Evaluacion.PreparacionExamen.UDPprimoscadenas;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * ServerStringInteger
 */
public class StringIntegerServer {

    private static final int MAX_LENGTH = (int) (Math.pow(2, 16) - 1); // = 65535
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try {
            DatagramSocket socket = new DatagramSocket(PORT); // Abre el socket en el puerto 8000
            byte[] receivedData = new byte[MAX_LENGTH];

            while (true) {
                DatagramPacket receivedPacket = new DatagramPacket(receivedData, receivedData.length);
                socket.receive(receivedPacket); // Espera y recibe el paquete

                new Thread(() -> {
                    // Extrae la información del paquete
                    int i;
                    String message = new String(receivedPacket.getData(), 0, receivedPacket.getLength());
                    String response;
                    try {
                        i = Integer.parseInt(message);
                        if (isPrime(i)) {
                            response = "A PRIME integer was received: " + i;
                        } else {
                            response = "A NORMAL integer was received: " + i;
                        }
                    } catch (Exception e) {
                        int[] count = processString(message);
                        response = "The received message has " + count[0] + " words, " + count[1] + " vowels and "
                                + count[2] + " consonants.";
                    }

                    byte[] sendData = response.getBytes();

                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,
                            receivedPacket.getAddress(), receivedPacket.getPort());
                    try {
                        socket.send(sendPacket);
                    } catch (IOException e) {
                    } // Envía el paquete al servidor

                }).start();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static int[] processString(String message) {
        int words = 0;
        if (message.length() != 0) {
            words = 1;
        }
        int vowels = 0;
        int consonants = 0;
        for (int i = 0; i < message.length(); i++) {
            if (Character.isWhitespace(message.charAt(i))) {
                words++;
            } else if (Character.isLetter(message.charAt(i))) {
                if (isVowel(message.charAt(i))) {
                    vowels++;
                } else {
                    consonants++;
                }
            }
        }

        int[] result = { words, vowels, consonants };

        return result;
    }

    private static boolean isVowel(char charAt) {
        if (charAt == 'a' || charAt == 'e' || charAt == 'i' || charAt == 'o' || charAt == 'u') {
            return true;
        }
        return false;
    }

    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}