package controller.command;
import model.person.PersonDAOImpl;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        request.setAttribute("persons", persons);
        return RequestParams.PERSON_LIST_JSP;
    }
}
