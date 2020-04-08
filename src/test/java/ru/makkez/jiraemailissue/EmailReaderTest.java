package ru.makkez.jiraemailissue;

import org.junit.jupiter.api.Test;

import javax.mail.MessagingException;

public class EmailReaderTest {

    @Test
    public void whenRead() throws MessagingException {
        EmailReader emailReader = new EmailReader();
        emailReader.read();
    }
}
