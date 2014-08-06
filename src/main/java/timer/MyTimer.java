package timer;

import model.ConnectToDB;
import model.person.Person;
import param.RequestParams;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.*;

/**
 * Created by Margarita on 06.08.2014.
 */
public class MyTimer extends TimerTask {
    public void run() {
        Calendar c = Calendar.getInstance();
        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        month++;
        List persons = ConnectToDB.getInstance().selectPersonBirthDay(day,month);
        StringBuilder message = new StringBuilder();
        for(int i = 0; i < persons.size(); i++){
            message.append(((Person)persons.get(i)).getSurname());
            message.append(" ");
            message.append(((Person)persons.get(i)).getName());
            message.append(" ");
            message.append(((Person)persons.get(i)).getPatronymic());
            message.append(", ");
            message.append(((Person)persons.get(i)).getDateOfBirth());
            message.append(", ");
            message.append(((Person)persons.get(i)).getEmail());
            message.append("\r\n");
        }
        String host = "smtp.gmail.com";
        String port = "587";
        final String user = "xomrita@gmail.com";
        final String pass = "pavlovamarisha";
        Properties properties = new Properties();
        properties.put(RequestParams.bundle.getString("hostMail"), host);
        properties.put(RequestParams.bundle.getString("portMail"), port);
        properties.put(RequestParams.bundle.getString("authMail"), "true");
        properties.put(RequestParams.bundle.getString("strttlsMail"), "true");
        properties.put(RequestParams.bundle.getString("userMail"), user);
        properties.put(RequestParams.bundle.getString("passwordMail"), pass);
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, pass);
            }
        };

        Session session = Session.getInstance(properties, auth);

        // creates a new e-mail message
        Message msg = new MimeMessage(session);


        try {
            msg.setFrom(new InternetAddress(user));
            InternetAddress[] toAddresses = {new InternetAddress("xomrita@gmail.com")};
            msg.setRecipients(Message.RecipientType.TO, toAddresses);
            msg.setSubject("Birthday");
            msg.setSentDate(new Date());
            msg.setText(message.toString());
            Transport.send(msg);
        } catch (MessagingException e) {
            e.printStackTrace();
        }


            // sends the e-mail


        System.out.println( "Запуск задачи" );
    }
}
