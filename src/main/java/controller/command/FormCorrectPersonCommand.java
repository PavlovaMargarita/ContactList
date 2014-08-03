package controller.command;

import model.person.PersonDAOImpl;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public class FormCorrectPersonCommand implements Command {
    private static final String UPLOAD_DIRECTORY = "upload";
    private static final int THRESHOLD_SIZE     = 1024 * 1024 * 3;  // 3MB
    private static final int MAX_FILE_SIZE      = 1024 * 1024 * 40; // 40MB
    private static final int MAX_REQUEST_SIZE   = 1024 * 1024 * 50; // 50MB
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
//        Person person = new Person();
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
//        List phones = new ArrayList<Phone>();
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

        if (!ServletFileUpload.isMultipartContent(request))     {
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
                    String fileName = new File(item.getName()).getName();
                    String filePath = uploadPath + File.separator + fileName;
                    File storeFile = new File(filePath);

                    // saves the file on disk
                    item.write(storeFile);
                }
            }
            request.setAttribute("message", "Upload has been done successfully!");
        } catch (Exception ex) {
            request.setAttribute("message", "There was an error: " + ex.getMessage());
        }
//        PersonDAOImpl.getInstance().correctPerson(person);
        List persons = PersonDAOImpl.getInstance().getPersons();
        request.setAttribute("persons", persons);
        return RequestParams.PERSON_LIST_JSP;


    }
}
