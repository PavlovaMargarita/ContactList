package controller.command;

import model.person.Person;
import model.person.PersonDAOImpl;
import model.template.TemplateDAOImpl;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public class FormSearchPersonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List persons = PersonDAOImpl.getInstance().searchPersons(
                request.getParameter(RequestParams.SURNAME),
                request.getParameter(RequestParams.NAME),
                request.getParameter(RequestParams.PATRONYMIC),
                request.getParameter(RequestParams.AGE),
                request.getParameterValues(RequestParams.AGE_RANGE),
                request.getParameter(RequestParams.SEX),
                request.getParameter(RequestParams.NATIONALITY),
                request.getParameter(RequestParams.MARITAL_STATUS),
                request.getParameter(RequestParams.COUNTRY),
                request.getParameter(RequestParams.CITY),
                request.getParameter(RequestParams.STREET),
                request.getParameter(RequestParams.HOME),
                request.getParameter(RequestParams.FLAT),
                request.getParameter(RequestParams.INDEX)
        );
        int goToPage = 1;
        List personForPage = new ArrayList<Person>();

        for (int i = (goToPage - 1) * 20; i < goToPage * 20 && i < persons.size(); i++) {
            personForPage.add(persons.get(i));
        }
        request.setAttribute(RequestParams.PERSONS, personForPage);
        request.setAttribute(RequestParams.CURRENT_PAGE, goToPage);
        if (goToPage != 1) {
            request.setAttribute(RequestParams.PREVIOUS_PAGE, goToPage - 1);
        }
        if (goToPage != persons.size() / 20 + 1) {
            request.setAttribute(RequestParams.NEXT_PAGE, goToPage + 1);
        }

        request.setAttribute(RequestParams.PERSONS, personForPage);
        List templates = TemplateDAOImpl.getInstance().getTemplate();
        request.setAttribute(RequestParams.TEMPLATES, templates);
        return RequestParams.PERSON_LIST_JSP;

    }
}
