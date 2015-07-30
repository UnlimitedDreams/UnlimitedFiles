/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.swing.JOptionPane;

/**
 *
 * @author Britt
 */
public class Mail {

    public String mensaje(int i, String x) {
        String mns = "";
        switch (i) {
            case 1:
                mns = "Buen Dia se le ha asignado revisar "
                        + "un proyecto que subieron nuevo el cual se llama " + x;
                break;
            case 2:
                mns = "Buen Dia  se ah aprobado el proyecto " + x;
                break;
            case 3:
                mns = "Buen Dia  se ah Desaprobado el proyecto " + x;
                break;
            case 4:
                mns = "Buena dia el comite a aprobado su proyecto" + x;
                break;

        }
        return mns;
    }

//    public boolean SendMail(int condi, String para, String Subject, String x) {
//        String mensaje = mensaje(condi, x);
//        String Username = "jdcastrilon@gmail.com";
//        String PassWord = "Jddreams";
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//        boolean r = false;
////        Session session = Session.getInstance(props,null);
//        Session session = Session.getInstance(props,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(Username, PassWord);
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(Username));
//            message.setRecipients(Message.RecipientType.TO,
//                    InternetAddress.parse(para));
//            message.setSubject(Subject);
//            message.setText(mensaje);
//
//            Transport.send(message);
//            System.out.println("Su mensaje ha sido enviado");
//            r = true;
//        } catch (MessagingException e) {
//            r = false;
//            System.out.println("Errorr " + e.toString());
//            throw new RuntimeException(e);
//
//        }
//        return r;
//    }
    public boolean SendMail(int condi, String para, String Subject, String x) {
        String mensaje = mensaje(condi, x);
        boolean r=false;
        Properties props = new Properties();
        props.put("mail.smtp.user", "jdcastrilon@gmail.com");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.socketFactory.fallback", "false");

        SecurityManager security = System.getSecurityManager();

        try {
            Authenticator auth = new autentificadorSMTP();
            Session session = Session.getInstance(props, auth);
            // session.setDebug(true);

            MimeMessage msg = new MimeMessage(session);
            msg.setText(mensaje);
            msg.setSubject(Subject);
            msg.setFrom(new InternetAddress("jdcastrilon@gmail.com"));
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    para));
            Transport.send(msg);
            r=true;
        } catch (Exception mex) {
            System.out.println("Error " + mex.toString() );
            r=false;
            mex.printStackTrace();
        }
        return r;
    }

    private class autentificadorSMTP extends javax.mail.Authenticator {

        @Override
        public PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication("jdcastrilon@gmail.com", "Jddreams");
        }
    }

}
