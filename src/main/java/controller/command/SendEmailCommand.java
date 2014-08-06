package controller.command;

import logger.LoggerApplication;
import model.StringTemplateInsert;
import model.person.PersonDAOImpl;
import model.template.TemplateDAOImpl;
import param.RequestParams;

import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Margarita on 05.08.2014.
 */
public class SendEmailCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {

        try {
            String usersReq = request.getParameter(RequestParams.EMAILS);
            String subjectReq = request.getParameter(RequestParams.SUBJECT);
            String templateReq = request.getParameter(RequestParams.TEMPLATE);
            String usersIDReq = request.getParameter(RequestParams.PERSONS_ID_FOR_EMAIL);

            List<String> usersEmail = new ArrayList<String>();
            StringTokenizer stringTokenizer = new StringTokenizer(usersReq, "; ");
            while (stringTokenizer.hasMoreTokens()) {
                usersEmail.add(stringTokenizer.nextToken());
            }
            List<String> usersID = new ArrayList<String>();
            stringTokenizer = new StringTokenizer(usersIDReq, "; ");
            while (stringTokenizer.hasMoreTokens()) {
                usersID.add(stringTokenizer.nextToken());
            }

            String template = "";
            stringTokenizer = new StringTokenizer(templateReq, "/");
            stringTokenizer.nextToken();
            if(stringTokenizer.hasMoreTokens()){
                template = stringTokenizer.nextToken();
            }

            String host = request.getSession().getServletContext().getInitParameter(RequestParams.HOST);
            String port = request.getSession().getServletContext().getInitParameter(RequestParams.PORT);
            final String user = request.getSession().getServletContext().getInitParameter(RequestParams.USER);
            final String pass = request.getSession().getServletContext().getInitParameter(RequestParams.PASS);
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

            for (int i = 0; i < usersID.size(); i++) {
                String messageReq = "";
                if (!templateReq.equals("none")) {
                    messageReq = StringTemplateInsert.getInstance().insert(template, PersonDAOImpl.getInstance().getPerson(Integer.parseInt(usersID.get(i))));
                } else {
                    messageReq = request.getParameter(RequestParams.MESSAGE);
                }
                msg.setFrom(new InternetAddress(user));
                InternetAddress[] toAddresses = {new InternetAddress(usersEmail.get(i))};
                msg.setRecipients(Message.RecipientType.TO, toAddresses);
                msg.setSubject(subjectReq);
                msg.setSentDate(new Date());
                msg.setText(messageReq);
                // sends the e-mail
                Transport.send(msg);

            }
        } catch (AddressException e) {
            LoggerApplication.getInstance().setError(e.getMessage());
        } catch (MessagingException e) {
            LoggerApplication.getInstance().setError(e.getMessage());
        }finally {
            int goToPage = 1;
            List personForPage = PersonDAOImpl.getInstance().getPersons(goToPage);
            request.setAttribute(RequestParams.PERSONS, personForPage);
            request.setAttribute(RequestParams.CURRENT_PAGE, goToPage);
            int preciousPage = PersonDAOImpl.getInstance().getPreviousPage(goToPage);
            int nextPage = PersonDAOImpl.getInstance().getNextPage(goToPage);
            if (preciousPage != 0) {
                request.setAttribute(RequestParams.PREVIOUS_PAGE, preciousPage);
            }
            if (nextPage != 0) {
                request.setAttribute(RequestParams.NEXT_PAGE, nextPage);
            }

            List templates = TemplateDAOImpl.getInstance().getTemplate();
            request.setAttribute(RequestParams.TEMPLATES, templates);
            return RequestParams.PERSON_LIST_JSP;
        }


    }
}
