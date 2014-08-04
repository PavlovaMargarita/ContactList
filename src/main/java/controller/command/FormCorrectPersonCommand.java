package controller.command;

import model.FilePerson;
import model.person.Person;
import model.person.PersonDAOImpl;
import model.phone.Phone;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public class FormCorrectPersonCommand implements Command {
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person();
//        String id = request.getParameter(RequestParams.ID);
//        if (!id.equals("")) {
//            person.setId(Integer.parseInt(id));
//        }
//        person.setSurname(request.getParameter(RequestParams.SURNAME));
//        person.setName(request.getParameter(RequestParams.NAME));
//        person.setPatronymic(request.getParameter(RequestParams.PATRONYMIC));
//        person.setDateOfBirth(request.getParameter(RequestParams.DATE_OF_BIRTH));
//        person.setSex(request.getParameter(RequestParams.SEX));
//        person.setNationality(request.getParameter(RequestParams.NATIONALITY));
//        person.setMaritalStatus(request.getParameter(RequestParams.MARITAL_STATUS));
//        person.setWebSite(request.getParameter(RequestParams.WEB_SITE));
//        person.setEmail(request.getParameter(RequestParams.EMAIL));
//        person.setCompany(request.getParameter(RequestParams.COMPANY));
//        person.setCountry(request.getParameter(RequestParams.COUNTRY));
//        person.setCity(request.getParameter(RequestParams.CITY));
//        person.setStreet(request.getParameter(RequestParams.STREET));
//        person.setHome(Integer.parseInt(request.getParameter(RequestParams.HOME)));
//        person.setFlat(Integer.parseInt(request.getParameter(RequestParams.FLAT)));
//        person.setIndex(request.getParameter(RequestParams.INDEX));
//
//        String[] idPhone = request.getParameterValues(RequestParams.ID_ID);
//        String[] countryCode = request.getParameterValues(RequestParams.COUNTY_CODE_ID);
//        String[] operatorCode = request.getParameterValues(RequestParams.OPERATOR_CODE_ID);
//        String[] phoneNumber = request.getParameterValues(RequestParams.PHONE_NUMBER_ID);
//        String[] phoneType = request.getParameterValues(RequestParams.PHONE_TYPE_ID);
//        String[] comment = request.getParameterValues(RequestParams.COMMENT_PHONE_ID);
        List phones = new ArrayList<Phone>();
//        if (countryCode != null) {
//            for (int i = 0; i < countryCode.length; i++) {
//                Phone phone = new Phone();
//                try {
//                    phone.setId(Integer.parseInt(idPhone[i]));
//                } catch (NullPointerException e) {
//                    phone.setId(0);
//                } catch (NumberFormatException e) {
//                    phone.setId(0);
//                }
//                phone.setCountryCode(Integer.parseInt(countryCode[i]));
//                phone.setOperatorCode(Integer.parseInt(operatorCode[i]));
//                phone.setPhoneNumber(Integer.parseInt(phoneNumber[i]));
//                phone.setPhoneType(phoneType[i]);
//                phone.setComment(comment[i]);
//                phones.add(phone);
//            }
//        }
//        person.setPhone(phones);

        List files = new ArrayList<FilePerson>();
        if (!ServletFileUpload.isMultipartContent(request)) {
            System.out.println("Request does not contain upload data");
        }

        // configures upload settings
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(THRESHOLD_SIZE);
        factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setFileSizeMax(MAX_FILE_SIZE);
        upload.setSizeMax(MAX_REQUEST_SIZE);

        // constructs the directory path to store upload file
        String uploadPath = request.getSession().getServletContext().getRealPath("")
                + File.separator + UPLOAD_DIRECTORY;
        // creates the directory if it does not exist
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
                    if (!item.getFieldName().equals("photoFile")) {
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

//                    String fileName = new File(item.getName()).getName();
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
                    }
                    else{
                        String photoName =  new File(item.getName()).getName();
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
                        person.setPhotoPath(photoPath);

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
            request.setAttribute("message", "Upload has been done successfully!");
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
        person.setPhone(phones);
        person.setFile(files);
        PersonDAOImpl.getInstance().correctPerson(person);
        List persons = PersonDAOImpl.getInstance().getPersons();
        request.setAttribute("persons", persons);
        return RequestParams.PERSON_LIST_JSP;


    }
}
