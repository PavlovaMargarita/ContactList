package controller.command;

import model.person.Person;
import model.person.PersonDAOImpl;
import param.RequestParams;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Margarita on 23.07.2014.
 */
public class ShowAllPersonCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List persons = PersonDAOImpl.getInstance().getPersons();
        int goToPage = Integer.parseInt(request.getParameter(RequestParams.GO_TO_PAGE));
        List personForPage = new ArrayList<Person>();
        for(int i = (goToPage - 1) * 20; i < goToPage * 20 && i < persons.size(); i++ ){
            personForPage.add(persons.get(i));
        }
        request.setAttribute("persons", personForPage);
        request.setAttribute("currentPage", goToPage);
        if(goToPage != 1){
            request.setAttribute("previousPage", goToPage - 1);
        }
        if(goToPage != persons.size()/20 + 1){
            request.setAttribute("nextPage", goToPage + 1);
        }
        return RequestParams.PERSON_LIST_JSP;
    }
}
