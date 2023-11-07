package com.enzosagnelonge.services;

import com.enzosagnelonge.data.Movement;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import java.util.Properties;
@Service
public class MailService {
    private final Session session;

    private final InternetAddress fromMail;
    private final Address[] toMail;

    public MailService() {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.freesmtpservers.com");
        props.put("mail.smtp.port", 25);
        props.put("mail.smtp.auth", false);
        props.put("mail.smtp.starttls.enable",true);

        session = Session.getInstance(props);

        try {
            fromMail = new InternetAddress("enzo.sagnelonge@gmail.com");
            toMail = InternetAddress.parse("enzo.sagnelonge@gmail.com");
        } catch (AddressException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMail(Movement movement){
        try {
            Message message = new MimeMessage(session);
            message.setFrom(fromMail);
            message.setRecipients(
                    Message.RecipientType.TO,
                    toMail
            );
            message.setSubject("FreightManager - Movement Declaration");;

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
