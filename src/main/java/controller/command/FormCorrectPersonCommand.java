package controller.command;

import logger.LoggerApplication;
import model.company.Company;
import model.company.CompanyDAOImpl;
import model.country.Country;
import model.country.CountryDAOImpl;
import model.filePerson.FilePerson;
import model.maritalStatus.MaritalStatus;
import model.maritalStatus.MaritalStatusDAOImpl;
import model.person.Person;
import model.person.PersonDAOImpl;
import model.phone.Phone;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
                            try {
                                person.setId(Integer.parseInt(value));
                            }catch (NumberFormatException e){
                                LoggerApplication.getInstance().setError("request have incorrect ID:" + e.getMessage());
                            }
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
                        Pattern pattern = Pattern.compile("^(0?[1-9]|[12][0-9]|3[01])[\\-](0?[1-9]|1[012])[\\-]\\d{4}$");
                        Matcher matcher = pattern.matcher(value);
                        if (matcher.matches()){
                            person.setDateOfBirth(value);
                        } else{
                            person.setWebSite("01-01-2000");
                            LoggerApplication.getInstance().setError("request have incorrect DATE OF BIRTH");
                        }
                    }
                    if (fieldName.equals(RequestParams.SEX)) {
                        if(value.equals("m") || value.equals("f")) {
                            person.setSex(value);
                        }else{
                            person.setSex("m");
                            LoggerApplication.getInstance().setError("request have incorrect SEX");
                        }
                    }
                    if (fieldName.equals(RequestParams.NATIONALITY)) {
                        person.setNationality(value);
                    }
                    if (fieldName.equals(RequestParams.MARITAL_STATUS)) {
                        List <MaritalStatus> maritalStatusFull = MaritalStatusDAOImpl.getInstance().getMaritalStatus();
                        List maritalStatusID = new ArrayList();
                        for(MaritalStatus maritalStatus:maritalStatusFull){
                            maritalStatusID.add(maritalStatus.getId());
                        }
                        if(maritalStatusID.contains(Integer.parseInt(value))) {
                            person.setMaritalStatus(value);
                        } else{
                            if(maritalStatusID.size() > 1) {
                                person.setMaritalStatus((String)maritalStatusID.get(0));
                            }
                            else{
                                person.setMaritalStatus("0");
                            }
                            LoggerApplication.getInstance().setError("request have incorrect MARITAL STATUS");
                        }
                    }
                    if (fieldName.equals(RequestParams.WEB_SITE)) {
                        Pattern pattern = Pattern.compile("(ftp|http|https):\\/\\/(\\w+:{0,1}\\w*@)?(\\S+)(:[0-9]+)?(\\/|\\/([\\w#!:.?+=&%@!\\-\\/]))?");
                        Matcher matcher = pattern.matcher(value);
                        if (matcher.matches()){
                            person.setWebSite(value);
                        } else{
                            person.setWebSite("http://www.google.com");
                            LoggerApplication.getInstance().setError("request have incorrect WEB SITE");
                        }

                    }
                    if (fieldName.equals(RequestParams.EMAIL)) {
                        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\\\+]+(\\\\.[_A-Za-z0-9-]+)*\n" +
                                "      @[A-Za-z0-9-]+(\\\\.[A-Za-z0-9]+)*(\\\\.[A-Za-z]{2,})$");
                        Matcher matcher = pattern.matcher(value);
                        if (matcher.matches()){
                            person.setEmail(value);
                        } else{
                            person.setEmail("test@test.test");
                            LoggerApplication.getInstance().setError("request have incorrect EMAIL");
                        }
                    }
                    if (fieldName.equals(RequestParams.COMPANY)) {
                        List <Company>companyFull = CompanyDAOImpl.getInstance().getCompanies();
                        List companyID = new ArrayList();
                        for(Company company:companyFull){
                            companyID.add(company.getId());
                        }
                        if(companyID.contains(Integer.parseInt(value))) {
                            person.setCompany(value);
                        } else{
                            if(companyID.size() > 1) {
                                person.setCompany((String) companyID.get(0));
                            }
                            else{
                                person.setCompany("0");
                            }
                            LoggerApplication.getInstance().setError("request have incorrect COMPANY");
                        }
                    }
                    if (fieldName.equals(RequestParams.COUNTRY)) {
                        List <Country> countryFull = CountryDAOImpl.getInstance().getCountries();
                        List countryID = new ArrayList();
                        for(Country country: countryFull){
                            countryID.add(country.getId());
                        }
                        if(countryID.contains(Integer.parseInt(value))) {
                            person.setCountry(value);
                        } else{
                            if(countryID.size() > 1) {
                                person.setCountry((String)countryID.get(0));
                            }
                            else{
                                person.setCountry("0");
                            }
                            LoggerApplication.getInstance().setError("request have incorrect COUNTRY");
                        }
                    }
                    if (fieldName.equals(RequestParams.CITY)) {
                        person.setCity(value);
                    }
                    if (fieldName.equals(RequestParams.STREET)) {
                        person.setStreet(value);
                    }
                    if (fieldName.equals(RequestParams.HOME)) {
                        try{
                            person.setHome(Integer.parseInt(value));
                        } catch (NumberFormatException e){
                            LoggerApplication.getInstance().setError("request have incorrect HOME:" + e.getMessage());
                        }
                    }
                    if (fieldName.equals(RequestParams.FLAT)) {
                        try {
                            person.setFlat(Integer.parseInt(value));
                        }catch (NumberFormatException e){
                            LoggerApplication.getInstance().setError("request have incorrect FLAT:" + e.getMessage());
                        }
                    }
                    if (fieldName.equals(RequestParams.INDEX)) {
                        person.setIndex(value);
                    }
                    if (fieldName.equals(RequestParams.COUNTY_CODE_ID)) {
                        Phone phone = new Phone();
                        try {
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
                        } catch (NumberFormatException e){
                            LoggerApplication.getInstance().setError("request have incorrect PHONE:" + e.getMessage());
                        }
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
            Person test = new Person();
            String oldDate = "";
            String newDate = person.getDateOfBirth();
            if(person.getId() != 0){
                test = PersonDAOImpl.getInstance().getPerson(person.getId());
                oldDate = test.getDateOfBirth();
            }
            PersonDAOImpl.getInstance().correctPerson(person);
            if(!oldDate.equals(newDate)) {

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
                    String host = RequestParams.bundle.getString("useHost");
                    String port = RequestParams.bundle.getString("usePort");
                    final String user = RequestParams.bundle.getString("adminLog");
                    final String pass = RequestParams.bundle.getString("adminPas");
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
                    InternetAddress[] toAddresses = {new InternetAddress(RequestParams.bundle.getString("adminLog"))};
                    msg.setRecipients(Message.RecipientType.TO, toAddresses);
                    msg.setSubject("Birthday");
                    msg.setSentDate(new Date());
                    msg.setText(message.toString());
                    Transport.send(msg);
                }
            }


        } catch (Exception ex) {
            LoggerApplication.getInstance().setError(ex.getMessage());
        } finally {
            return RequestParams.INDEX_JSP;
        }


    }
}
