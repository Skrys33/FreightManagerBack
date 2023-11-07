package com.enzosagnelonge.services;

import com.enzosagnelonge.data.Movement;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.util.Properties;

public class MailService {
    public static void sendMail(Movement movement){
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.freesmtpservers.com");
        props.put("mail.smtp.port", "25");
        props.put("mail.smtp.auth", "false");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("enzo.sagnelonge@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse("enzo.sagnelonge@gmail.com")
            );
            message.setSubject("FreightManager - Movement Declaration");
            message.setText("Dear Mail Crawler, Please do not spam my email!");

            try {
                MimeBodyPart xmlAttachment = new MimeBodyPart();
                xmlAttachment.setContent(XMLMovementConverterService.getXMlFile(movement), "text/xml");
                xmlAttachment.setFileName("Movement_" + movement.getTypeMovement() + "_" + movement.getGoods().getReference() + "_" + movement.getMovementDateTime().toString() +".xml");

                Multipart multipart = new MimeMultipart();
                multipart.addBodyPart(xmlAttachment);

                message.setContent(multipart);
            } catch (ParserConfigurationException | TransformerException e) {
                throw new RuntimeException(e);
            }

            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
