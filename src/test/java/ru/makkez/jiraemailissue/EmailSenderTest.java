package ru.makkez.jiraemailissue;

import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;

public class EmailSenderTest {

    @Test
    public void whenSendSimpleEmail() throws MessagingException {
        String text = "Hello!";
        EmailSender emailSender = new EmailSender();
        emailSender.send(text);
    }

    @Test
    public void whenSendEmailWithFile() throws UnsupportedEncodingException, MessagingException {
        String text = "Hello!";
        String fileNmae = "testArchive.zip";
        EmailSender emailSender = new EmailSender();
        emailSender.send(text, fileNmae);
    }
}
