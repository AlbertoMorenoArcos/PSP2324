package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

import com.example.LectorParaSpam.ObservadorMensajes;

public class Main {
    public static void main(String[] args) throws IOException {

        String rutaMensajes = args[0];
        String rutaCorreos = args[1];
        String correo = args[2];
        String password = args[3];

        // String rutaMensajes =
        // "/home/alberto/PSP2324/PSP_2a_Evaluacion/PreparacionExamen/ejercicio2examen/src/main/java/com/example/mensajes.txt";
        // String rutaCorreos =
        // "/home/alberto/PSP2324/PSP_2a_Evaluacion/PreparacionExamen/ejercicio2examen/src/main/java/com/example/correos.txt";
        // String correo = "alberto.moreno37";
        // String password = "contraseñafalsa";
        LectorParaSpam lector1 = new LectorParaSpam(rutaMensajes, rutaCorreos);
        EnviadorSpam enviador1 = new EnviadorSpam(correo, password, lector1);
        // EnviadorSpam enviador2 = new EnviadorSpam(correo, password, lector1);

        lector1.addObservadores(enviador1);

        lector1.comenzarLectura();
    }

}
/*
 * mvn exec:java -Dexec.mainClass=com.example.Main -Dexec.
 * args="'/home/alberto/PSP2324/PSP_2a_Evaluacion/PreparacionExamen/ejercicio2examen/src/main/java/com/example/mensajes.txt' '/home/alberto/PSP2324/PSP_2a_Evaluacion/PreparacionExamen/ejercicio2examen/src/main/java/com/example/correos.txt' 'alberto.moreno37' 'Almoar.95'"
 */