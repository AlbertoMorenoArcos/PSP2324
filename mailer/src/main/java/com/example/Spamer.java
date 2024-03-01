package com.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.simplejavamail.api.email.Email;
import org.simplejavamail.api.mailer.Mailer;
import org.simplejavamail.api.mailer.config.TransportStrategy;
import org.simplejavamail.config.ConfigLoader;
import org.simplejavamail.email.EmailBuilder;
import org.simplejavamail.mailer.MailerBuilder;

/**
 * Hello world!
 *
 */
public class Spamer {
    private static final String RUTA = "/home/alberto/PSP2324/mailer/src/main/java/com/example/dir.txt";

    public static void main(String[] args) throws IOException {
        // Most essentials together (but almost everything is optional):

        BufferedReader reader = new BufferedReader(new FileReader(RUTA));

        String line = "";
        String dirCorreo = "";

        while ((line = reader.readLine()) != null) {
            dirCorreo = line;

            // Código que envía
            Email email = EmailBuilder.startingBlank()
                    .to("Hola", dirCorreo)
                    .from("Alberto", "alberto.moreno37@educa.madrid.org")
                    .withReplyTo("Alberto", "alberto.moreno37@educa.madrid.org")
                    .withSubject("Proyecto Spamtoso")
                    .withHTMLText("<h1>Hola!!</h1><p>¿Qué tal?</p>")
                    .withPlainText("Hola! ¿Qué tal?")
                    .buildEmail();

            Mailer mailer = MailerBuilder
                    .withSMTPServer("smtp.educa.madrid.org", 587, "alberto.moreno37", "contraseñafalsa")
                    .withTransportStrategy(TransportStrategy.SMTP_TLS)
                    .clearEmailValidator() // turns off email validation
                    .buildMailer();
            mailer.sendMail(email);
        }
    }
}
