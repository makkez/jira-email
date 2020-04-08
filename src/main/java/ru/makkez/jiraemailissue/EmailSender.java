package ru.makkez.jiraemailissue;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailSender {

    public void send(String text) throws MessagingException {
        Properties properties = this.createProperties();
        Session session = this.createSession(properties);

        //Создаем новое почтовое сообщение
        Message message = new MimeMessage(session);
        //От кого
        message.setFrom(new InternetAddress("metallmaster@bk.ru"));
        //Кому
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("metallmaster@bk.ru"));
        //Тема письма
        message.setSubject("Очень важное тестовое письмо!!!");
        //Текст письма
        message.setText(text);
        //Поехали!!!
        Transport.send(message);
    }

    public void send(String text, String fileName) throws MessagingException, UnsupportedEncodingException {
        Properties properties = this.createProperties();
        Session session = this.createSession(properties);

        //Создаем новое почтовое сообщение
        Message message = new MimeMessage(session);
        //От кого
        message.setFrom(new InternetAddress("metallmaster@bk.ru"));
        //Кому
        message.setRecipient(Message.RecipientType.TO, new InternetAddress("metallmaster@bk.ru"));
        //Тема письма
        message.setSubject("Очень важное тестовое письмо с файлом!!!");

        //Файл вложения
        File file = new File(fileName);
//Собираем содержимое письма из кусочков
        MimeMultipart multipart = new MimeMultipart();
//Первый кусочек - текст письма
        MimeBodyPart part1 = new MimeBodyPart();
        part1.addHeader("Content-Type", "text/plain; charset=UTF-8");
        part1.setDataHandler(new DataHandler(text, "text/plain; charset=\"utf-8\""));
        multipart.addBodyPart(part1);

//Второй кусочек - файл
        MimeBodyPart part2 = new MimeBodyPart();
        part2.setFileName(MimeUtility.encodeWord(file.getName()));
        part2.setDataHandler(new DataHandler(new FileDataSource(file)));
        multipart.addBodyPart(part2);
//Добавляем оба кусочка в сообщение
        message.setContent(multipart);

        //Поехали!!!
        Transport.send(message);
    }

    public Properties createProperties() {
        Properties result = new Properties();
        //Хост или IP-адрес почтового сервера
        result.put("mail.smtp.host", "smtp.mail.ru");
        //Требуется ли аутентификация для отправки сообщения
        result.put("mail.smtp.auth", "true");
        //Порт для установки соединения
        result.put("mail.smtp.socketFactory.port", "465");
        //Фабрика сокетов, так как при отправке сообщения Yandex требует SSL-соединения
        result.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
        return result;
    }

    public Session createSession(Properties properties) {
        //Создаем соединение для отправки почтового сообщения
         return Session.getDefaultInstance(properties,
                //Аутентификатор - объект, который передает логин и пароль
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("metallmaster@bk.ru", "***");
                    }
                });
    }
}
