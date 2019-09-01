/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ledVan.Util;

/**
 *
 * @author santopat
 */
import com.ledVan.model.SMTPDetails;
import java.util.Date;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHelper {

    public static void sendMail(SMTPDetails mTPDetails, String reportFilePath) {

        try {
            
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", mTPDetails.getHostName());
            props.put("mail.smtp.port", 587);

            Session session = Session.getInstance(props, new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("santhu.gouda@gmail.com", "alliswell@100");
                }
            });

            Message msg = new MimeMessage(session);
            msg.setFrom(new InternetAddress(mTPDetails.getFromEmail(), false));
            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(mTPDetails.getToEmail()));
            msg.setSubject(mTPDetails.getEmailDefaultSubject());
            msg.setContent(mTPDetails.getEmailDefaultBody(), "text/html");
            msg.setSentDate(new Date());

            MimeBodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setContent(mTPDetails.getEmailDefaultBody(), "text/html");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            MimeBodyPart attachPart = new MimeBodyPart();

            attachPart.attachFile(reportFilePath);
            multipart.addBodyPart(attachPart);
            msg.setContent(multipart);
            Transport.send(msg);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
