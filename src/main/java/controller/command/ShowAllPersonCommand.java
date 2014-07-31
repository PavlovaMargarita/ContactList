package controller.command;
import model.person.PersonDAOImpl;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public class ShowAllPersonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List persons = PersonDAOImpl.getInstance().getPersons();
        request.setAttribute("persons", persons);
        return RequestParams.PERSON_LIST_JSP;
    }
}
