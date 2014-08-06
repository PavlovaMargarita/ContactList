package controller.command;

import model.person.PersonDAOImpl;
import model.template.TemplateDAOImpl;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Margarita on 24.07.2014.
 */
public class DeletePersonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PersonDAOImpl.getInstance().deletePerson(request.getParameterValues(RequestParams.CHECK_PERSON));
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
