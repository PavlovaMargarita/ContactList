package controller.command;

import model.person.PersonDAOImpl;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Margarita on 24.07.2014.
 */
public class DeletePersonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        PersonDAOImpl.getInstance().deletePerson(request.getParameterValues(RequestParams.CHECK_PERSON));
        return RequestParams.INDEX_JSP;
    }
}
