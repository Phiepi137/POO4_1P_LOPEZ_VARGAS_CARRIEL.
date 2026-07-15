package com.example;

import jakarta.mail.*;
import jakarta.mail.internet.*;
import java.util.Properties;

public class EnviarCorreo {

    public static void enviar(String destinatario, String asunto, String contenido) {

        final String remitente = "raalg004@gmail.com";
        final String password = "nszj zmmr ozyh odor";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(remitente, password);
            }
        });

        try {
            Message mensaje = new MimeMessage(session);
            mensaje.setFrom(new InternetAddress(remitente));
            mensaje.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destinatario));
            mensaje.setSubject(asunto);
            mensaje.setText(contenido);

            Transport.send(mensaje);

            System.out.println("Correo enviado correctamente.");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}