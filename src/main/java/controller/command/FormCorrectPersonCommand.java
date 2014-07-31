package controller.command;

import model.person.Person;
import model.person.PersonDAOImpl;
import model.phone.Phone;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public class FormCorrectPersonCommand implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        Person person = new Person();
        String id = request.getParameter(RequestParams.ID);
        if (!id.equals("")) {
            person.setId(Integer.parseInt(id));
        }
        person.setSurname(request.getParameter(RequestParams.SURNAME));
        person.setName(request.getParameter(RequestParams.NAME));
        person.setPatronymic(request.getParameter(RequestParams.PATRONYMIC));
        person.setDateOfBirth(request.getParameter(RequestParams.DATE_OF_BIRTH));
        person.setSex(request.getParameter(RequestParams.SEX));
        person.setNationality(request.getParameter(RequestParams.NATIONALITY));
        person.setMaritalStatus(request.getParameter(RequestParams.MARITAL_STATUS));
        person.setWebSite(request.getParameter(RequestParams.WEB_SITE));
        person.setEmail(request.getParameter(RequestParams.EMAIL));
        person.setCompany(request.getParameter(RequestParams.COMPANY));
        person.setCountry(request.getParameter(RequestParams.COUNTRY));
        person.setCity(request.getParameter(RequestParams.CITY));
        person.setStreet(request.getParameter(RequestParams.STREET));
        person.setHome(Integer.parseInt(request.getParameter(RequestParams.HOME)));
        person.setFlat(Integer.parseInt(request.getParameter(RequestParams.FLAT)));
        person.setIndex(request.getParameter(RequestParams.INDEX));

        String[] idPhone = request.getParameterValues(RequestParams.ID_ID);
        String[] countryCode = request.getParameterValues(RequestParams.COUNTY_CODE_ID);
        String[] operatorCode = request.getParameterValues(RequestParams.OPERATOR_CODE_ID);
        String[] phoneNumber = request.getParameterValues(RequestParams.PHONE_NUMBER_ID);
        String[] phoneType = request.getParameterValues(RequestParams.PHONE_TYPE_ID);
        String[] comment = request.getParameterValues(RequestParams.COMMENT_PHONE_ID);
        List phones = new ArrayList<Phone>();
        if (countryCode != null) {
            for (int i = 0; i < countryCode.length; i++) {
                Phone phone = new Phone();
                try {
                    phone.setId(Integer.parseInt(idPhone[i]));
                } catch (NullPointerException e) {
                    phone.setId(0);
                } catch (NumberFormatException e) {
                    phone.setId(0);
                }
                phone.setCountryCode(Integer.parseInt(countryCode[i]));
                phone.setOperatorCode(Integer.parseInt(operatorCode[i]));
                phone.setPhoneNumber(Integer.parseInt(phoneNumber[i]));
                phone.setPhoneType(phoneType[i]);
                phone.setComment(comment[i]);
                phones.add(phone);
            }
        }
        person.setPhone(phones);
        PersonDAOImpl.getInstance().correctPerson(person);

        String[] fileNames = request.getParameterValues("fileName");
        String fileName = fileNames[0];
        if (fileName == null || fileName.equals("")) {
            System.out.println("File Name can't be null or empty");
        }
        File file = new File(request.getSession().getServletContext().getAttribute("FILES_DIR") + File.separator + fileName);
        if (!file.exists()) {
            System.out.println("File doesn't exists on server.");
        }
        System.out.println("File location on server::" + file.getAbsolutePath());

        List persons = PersonDAOImpl.getInstance().getPersons();
        request.setAttribute("persons", persons);
        return RequestParams.PERSON_LIST_JSP;


    }
}
