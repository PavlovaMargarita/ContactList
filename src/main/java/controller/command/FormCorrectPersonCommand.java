package controller.command;

import logger.LoggerApplication;
import model.filePerson.FilePerson;
import model.person.Person;
import model.person.PersonDAOImpl;
import model.phone.Phone;
import model.template.TemplateDAOImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import param.RequestParams;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.*;

/**
 * Created by Margarita on 23.07.2014.
 */
public class FormCorrectPersonCommand implements Command {
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person();
        List phones = new ArrayList<Phone>();
        List files = new ArrayList<FilePerson>();
        if (!ServletFileUpload.isMultipartContent(request)) {
            LoggerApplication.getInstance().setError("Request does not contain upload data");
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty(RequestParams.bundle.getString("propertyRepository"))));
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);
        String uploadPath = RequestParams.bundle.getString("uploadPath");
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdir();
        }

        try {
            // parses the request's content to extract file data
            List formItems = upload.parseRequest(request);
            Iterator iter = formItems.iterator();

            // iterates over form's fields
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
                // processes only fields that are not form fields
                if (!item.isFormField()) {
                    if (!item.getFieldName().equals(RequestParams.PHOTO_FILE)) {
                        FilePerson filePerson = new FilePerson();

                        FileItem itemFileName = (FileItem) iter.next();
                        String fileName = itemFileName.getString("UTF-8").trim();
                        filePerson.setFileName(fileName);

                        itemFileName = (FileItem) iter.next();
                        String value = itemFileName.getString("UTF-8").trim();
                        filePerson.setFileDate(value);

                        Calendar cal = Calendar.getInstance();
                        String fileHash = fileName + value + cal.getTime().toString();
                        fileHash = Integer.toString(fileHash.hashCode());
                        StringBuilder format = new StringBuilder();
                        for (int i = fileName.length(); i > 0 && (fileName.charAt(i - 1) != '.'); i--) {
                            format.insert(0, fileName.charAt(i - 1));
                        }
                        String filePath = uploadPath + File.separator + fileHash + "." + format.toString();
                        File storeFile = new File(filePath);
                        // saves the file on disk
                        item.write(storeFile);

                        filePerson.setFileHash(filePath);
                        itemFileName = (FileItem) iter.next();
                        value = itemFileName.getString("UTF-8").trim();
                        filePerson.setComment(value);
                        files.add(filePerson);
                    } else {
                        String photoName = new File(item.getName()).getName();
                        if (!photoName.equals("")) {
                            Calendar cal = Calendar.getInstance();
                            String photoHash = photoName + cal.getTime().toString();
                            photoHash = Integer.toString(photoHash.hashCode());
                            StringBuilder format = new StringBuilder();
                            for (int i = photoName.length(); i > 0 && (photoName.charAt(i - 1) != '.'); i--) {
                                format.insert(0, photoName.charAt(i - 1));
                            }
                            String photoPath = uploadPath + File.separator + photoHash + "." + format.toString();
                            File storeFile = new File(photoPath);
                            // saves the file on disk
                            item.write(storeFile);
                            person.setPhotoPath(photoHash + "." + format.toString());
                        }

                    }

                } else {
                    String fieldName = item.getFieldName();
                    String value = item.getString("UTF-8").trim();
                    if (fieldName.equals(RequestParams.ID)) {
                        if (value.equals("")) {
                            person.setId(0);
                        } else {
                            person.setId(Integer.parseInt(value));
                        }

                    }
                    if (fieldName.equals(RequestParams.PHOTO_PATH)) {
                        if (person.getPhotoPath() == null) {
                            person.setPhotoPath(value);
                        }
                    }
                    if (fieldName.equals(RequestParams.SURNAME)) {
                        person.setSurname(value);
                    }
                    if (fieldName.equals(RequestParams.NAME)) {
                        person.setName(value);
                    }
                    if (fieldName.equals(RequestParams.PATRONYMIC)) {
                        person.setPatronymic(value);
                    }
                    if (fieldName.equals(RequestParams.DATE_OF_BIRTH)) {
                        person.setDateOfBirth(value);
                    }
                    if (fieldName.equals(RequestParams.SEX)) {
                        person.setSex(value);
                    }
                    if (fieldName.equals(RequestParams.NATIONALITY)) {
                        person.setNationality(value);
                    }
                    if (fieldName.equals(RequestParams.MARITAL_STATUS)) {
                        person.setMaritalStatus(value);
                    }
                    if (fieldName.equals(RequestParams.WEB_SITE)) {
                        person.setWebSite(value);
                    }
                    if (fieldName.equals(RequestParams.EMAIL)) {
                        person.setEmail(value);
                    }
                    if (fieldName.equals(RequestParams.COMPANY)) {
                        person.setCompany(value);
                    }
                    if (fieldName.equals(RequestParams.COUNTRY)) {
                        person.setCountry(value);
                    }
                    if (fieldName.equals(RequestParams.CITY)) {
                        person.setCity(value);
                    }
                    if (fieldName.equals(RequestParams.STREET)) {
                        person.setStreet(value);
                    }
                    if (fieldName.equals(RequestParams.HOME)) {
                        person.setHome(Integer.parseInt(value));
                    }
                    if (fieldName.equals(RequestParams.FLAT)) {
                        person.setFlat(Integer.parseInt(value));
                    }
                    if (fieldName.equals(RequestParams.INDEX)) {
                        person.setIndex(value);
                    }
                    if (fieldName.equals(RequestParams.COUNTY_CODE_ID)) {
                        Phone phone = new Phone();
                        phone.setCountryCode(Integer.parseInt(value));
                        item = (FileItem) iter.next();
                        value = item.getString("UTF-8").trim();
                        phone.setOperatorCode(Integer.parseInt(value));
                        item = (FileItem) iter.next();
                        value = item.getString("UTF-8").trim();
                        phone.setPhoneNumber(Integer.parseInt(value));
                        item = (FileItem) iter.next();
                        value = item.getString("UTF-8").trim();
                        phone.setPhoneType(value);
                        item = (FileItem) iter.next();
                        value = item.getString("UTF-8").trim();
                        phone.setComment(value);
                        phones.add(phone);
                    }
                    if (fieldName.equals(RequestParams.FILE_HASH)) {
                        FilePerson filePerson = new FilePerson();
                        filePerson.setFileHash(value);
                        item = (FileItem) iter.next();
                        value = item.getString("UTF-8").trim();
                        filePerson.setFileName(value);
                        item = (FileItem) iter.next();
                        value = item.getString("UTF-8").trim();
                        filePerson.setFileDate(value);
                        item = (FileItem) iter.next();
                        value = item.getString("UTF-8").trim();
                        filePerson.setComment(value);
                        files.add(filePerson);
                    }
                }
            }
            person.setPhone(phones);
            person.setFile(files);
            PersonDAOImpl.getInstance().correctPerson(person);
            String dateOfBirth = person.getDateOfBirth();
            StringTokenizer stringTokenizer = new StringTokenizer(dateOfBirth, "-");
            String dayBirth = stringTokenizer.nextToken();
            String monthBirth = stringTokenizer.nextToken();
            Calendar c = Calendar.getInstance();
            int day = c.get(Calendar.DAY_OF_MONTH);
            int month = c.get(Calendar.MONTH);
            month++;
            if (day == Integer.parseInt(dayBirth) && month == Integer.parseInt(monthBirth)) {
                StringBuilder message = new StringBuilder();
                message.append(person.getSurname());
                message.append(" ");
                message.append(person.getName());
                message.append(" ");
                message.append(person.getPatronymic());
                message.append(", ");
                message.append(person.getDateOfBirth());
                message.append(", ");
                message.append(person.getEmail());
                message.append("\r\n");
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
                Authenticator auth = new Authenticator() {
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(user, pass);
                    }
                };
                Session session = Session.getInstance(properties, auth);
                Message msg = new MimeMessage(session);

                msg.setFrom(new InternetAddress(user));
                InternetAddress[] toAddresses = {new InternetAddress("xomrita@gmail.com")};
                msg.setRecipients(Message.RecipientType.TO, toAddresses);
                msg.setSubject("Birthday");
                msg.setSentDate(new Date());
                msg.setText(message.toString());
                Transport.send(msg);
            }


        } catch (Exception ex) {
            LoggerApplication.getInstance().setError(ex.getMessage());
        } finally {
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
