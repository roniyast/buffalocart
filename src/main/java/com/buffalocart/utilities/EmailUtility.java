package com.buffalocart.utilities;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;

public class EmailUtility {

    String username;
    String password;
    String fileName;
    String filePath;
    String rMailId;
    List<String> fileNames;
    Properties prop;

    public EmailUtility(String filePath, String fileName, String rMailId, List<String> fileNames, Properties prop) {
        this.prop = prop;
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileNames = fileNames;
        this.rMailId = rMailId;
    }

    public void sendEmail() throws MessagingException, IOException {

        username = prop.getProperty("from_email");
        password = prop.getProperty("from_password");
        String eDate = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", true);
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        };
        Session session = Session.getInstance(props, auth);
        String file = filePath + "//" + fileName;
        fileNames.add(file);

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("mail@gmail.com"));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(rMailId));
        message.setSubject("Demo Web Shop_" + eDate);

        BodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setText("Dear Stakeholder,\n" +
                "\n" +
                "These are the test results of \"Demo Web Shop Project\" . Automation execution was conducted on " + eDate + ".\n" +
                "\n" +
                "Thanks & Regards,\n" +
                "Automation Team");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
        for (int i = 0; i < fileNames.size(); i++) {

            if (fileNames.get(i).contains(eDate)) {
                MimeBodyPart attachmentPart = new MimeBodyPart();
                attachmentPart.attachFile(fileNames.get(i));
                multipart.addBodyPart(attachmentPart);
            }
        }
        message.setContent(multipart);
        Transport.send(message);
    }

}